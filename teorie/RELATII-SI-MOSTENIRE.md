# Relații între entități & Moștenire — best practices

## Partea I — Relații

### Vocabular de bază

- **Owning side** (partea care deține relația) = entitatea în al cărei tabel stă cheia străină (FK). Ea decide ce se scrie în DB.
- **Inverse side** (partea inversă) = doar oglindește relația în Java; e marcată cu `mappedBy`. Modificările făcute DOAR pe partea inversă **nu se salvează**.
- **`mappedBy = "camp"`** = „relația e deja definită de câmpul `camp` din partea cealaltă — nu crea altă coloană/tabel”.
- **Direcție**: *unidirecțională* (o singură parte cunoaște relația) sau *bidirecțională* (ambele). Bidirecțional = mai multă putere, dar trebuie ținut sincronizat manual.

### Tabel comparativ — cele 4 tipuri

| tip | owning side (FK / join table) | inverse side | exemplu |
|---|---|---|---|
| `@ManyToOne` / `@OneToMany` | `@ManyToOne` (ține FK) | `@OneToMany(mappedBy)` | Book → Author |
| `@OneToOne` | `@OneToOne` + `@JoinColumn` | `@OneToOne(mappedBy)` | Citizen → Passport |
| `@ManyToMany` | `@ManyToMany` + `@JoinTable` | `@ManyToMany(mappedBy)` | Actor ↔ Film |

### 1. `@ManyToOne` / `@OneToMany` (1—*)

Cea mai frecventă relație. FK stă mereu pe partea „many”.

```java
@Entity
class Author {
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book b) {   // helper de sincronizare
        books.add(b);
        b.setAuthor(this);
    }
}

@Entity
class Book {
    @ManyToOne                       // owning side — ține coloana author_id
    private Author author;
}
```

Best practices:
- FK e pe „many” (`Book`), deci `@ManyToOne` e owning side; `@OneToMany` e mereu inverse cu `mappedBy`.
- Ține un **helper de sincronizare** (`addBook`) care setează ambele capete. Altfel `author_id` rămâne `null`.
- `cascade = ALL` + `orphanRemoval = true` doar când copiii **aparțin** părintelui (compoziție): scoaterea din listă = ștergere din DB.
- `@ManyToOne` e implicit **EAGER**, `@OneToMany` e **LAZY** — de obicei vrei ambele LAZY (`@ManyToOne(fetch = FetchType.LAZY)`).

### 2. `@OneToOne` (1—1)

```java
@Entity
class Citizen {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passport_id")     // owning side — ține FK-ul
    private Passport passport;
}
```

Best practices:
- Owning side primește `@JoinColumn`; dacă vrei bidirecțional, cealaltă parte pune `@OneToOne(mappedBy = "passport")`.
- Pune un `@OneToOne` doar când chiar e 1—1 real. Des, o coloană în plus pe o entitate existentă e mai simplă decât un tabel separat.
- Fii atent la fetch: `@OneToOne` e implicit EAGER și poate încărca lanțuri întregi.

### 3. `@ManyToMany` (*—*)

Are nevoie de un **tabel de legătură** (join table). Fără atribute pe relație.

```java
@Entity
class Actor {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "actor_film",
        joinColumns = @JoinColumn(name = "actor_id"),
        inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Film> films = new HashSet<>();

    public void addFilm(Film f) { films.add(f); f.getActors().add(this); }
}

@Entity
class Film {
    @ManyToMany(mappedBy = "films")
    private Set<Actor> actors = new HashSet<>();
}
```

Best practices:
- Folosește **`Set`**, nu `List` — evită duplicate și query-uri ineficiente la update.
- **NU** pune `CascadeType.REMOVE` pe `@ManyToMany` — ai șterge entități partajate (ștergi un actor și dispar filmele).
- Un singur owning side cu `@JoinTable`; celălalt `mappedBy`.
- Dacă relația are **atribute proprii** (ex: `rol`, `data`), nu mai e `@ManyToMany` — modelează o entitate de legătură (`Role`) cu două `@ManyToOne`.

### Cascade & fetch — pe scurt

| cascade | când |
|---|---|
| `PERSIST` / `MERGE` | vrei să salvezi copiii odată cu părintele |
| `ALL` | compoziție reală (părinte deține copiii) |
| (niciunul) | entități independente/partajate (ex: `@ManyToMany`) |

| fetch | implicit la |
|---|---|
| `LAZY` (se încarcă la cerere) | `@OneToMany`, `@ManyToMany` |
| `EAGER` (se încarcă imediat) | `@ManyToOne`, `@OneToOne` |

Regula de aur: **preferă LAZY** peste tot și încarcă explicit ce-ți trebuie (`join fetch`), ca să eviți `LazyInitializationException` și problema **N+1** (o interogare pentru părinți + câte una pentru fiecare copil).

---

## Partea II — Moștenire (JPA inheritance)

Când ai o ierarhie de clase (`Payment` → `CardPayment`, `CashPayment`), JPA o poate mapa în 3 strategii + o variantă „fără polimorfism”.

### Tabel comparativ — strategii

| strategie | tabele | avantaje | dezavantaje | când |
|---|---|---|---|---|
| **SINGLE_TABLE** (implicit) | 1 tabel + coloană discriminator | rapid, fără JOIN | coloane NULL pentru câmpuri specifice; nu poți pune `NOT NULL` pe ele | ierarhii mici, multe query-uri |
| **JOINED** | 1 tabel/clasă, legate prin FK | normalizat, `NOT NULL` merge | JOIN-uri la citire | date curate, ierarhii adânci |
| **TABLE_PER_CLASS** | 1 tabel/clasă concretă (fără cel de bază) | fără JOIN pe un tip | query-uri polimorfice cu `UNION`, id-uri greu de generat | rar |
| **`@MappedSuperclass`** | fără tabel pt. baza | reutilizezi câmpuri (ex: audit) | **fără query polimorfic** pe baza | câmpuri comune (`id`, `createdAt`) |

### SINGLE_TABLE (cel din ex09)

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
abstract class Payment {
    @Id @GeneratedValue Long id;
    double amount;
}

@Entity @DiscriminatorValue("CARD")
class CardPayment extends Payment { String cardNumber; }

@Entity @DiscriminatorValue("CASH")
class CashPayment extends Payment { }
```

- Un singur tabel `payment` cu o coloană `payment_type` care spune ce subtip e fiecare rând.
- `PaymentRepository extends JpaRepository<Payment, Long>` întoarce **polimorfic**: `findAll()` îți dă și `CardPayment`, și `CashPayment`.
- Best practice: pentru SINGLE_TABLE, câmpurile subclaselor trebuie să fie nullable (un rând CASH n-are `cardNumber`).

### `@MappedSuperclass` — cazul cel mai comun în practică

Nu pentru polimorfism, ci pentru **câmpuri comune reutilizate**:

```java
@MappedSuperclass
abstract class BaseEntity {
    @Id @GeneratedValue Long id;
    @Column(updatable = false) Instant createdAt;
}

@Entity
class Product extends BaseEntity { String name; }   // moștenește id + createdAt
```

- `BaseEntity` NU e entitate, nu are tabel; câmpurile ei apar în tabelul fiecărei subclase.
- Nu poți face `repository<BaseEntity>` — nu e polimorfic. Doar economisești cod repetat.

### Cum alegi

1. Ai nevoie de query polimorfic (un repository peste tipul de bază)? → `SINGLE_TABLE` (default, simplu) sau `JOINED` (dacă vrei schema curată).
2. Vrei doar să nu repeți `id`/`createdAt` în fiecare entitate? → `@MappedSuperclass`.
3. Începător / ierarhie mică? → **SINGLE_TABLE**. E cea mai simplă și cea mai rapidă.
