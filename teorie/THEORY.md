# Teorie — Spring Data JPA

## De unde vii și unde ajungi

La Lecția 01 (fundamentals) ai învățat cum Spring creează și injectează bean-uri. Acum vezi cum Spring **vorbește cu baza de date** fără să scrii SQL de mână și fără să scrii cod de acces la date (DAO).

Trei straturi suprapuse:

| strat | ce e | rolul lui |
|---|---|---|
| **JDBC** | API-ul Java standard de acces la DB | trimite SQL brut, primești `ResultSet` |
| **JPA** (Jakarta Persistence API) | standard de ORM (Object-Relational Mapping) | mapează obiecte Java ↔ rânduri în tabel |
| **Hibernate** | implementarea concretă de JPA | generează SQL-ul, gestionează sesiunea |
| **Spring Data JPA** | strat peste JPA/Hibernate | îți dă repository-uri gata făcute din interfețe |

Tu lucrezi aproape numai la nivelul de sus (Spring Data JPA), dar e bine să știi ce e dedesubt.

## 1. Entitatea — `@Entity`

O entitate e o clasă Java mapată pe un tabel.

```java
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    protected Product() { }              // JPA are nevoie de un constructor fără argumente

    public Product(String name, double price) { ... }
    // getteri
}
```

- `@Entity` — marchează clasa ca gestionată de JPA. Fără ea: *"Not a managed type"*.
- `@Id` — cheia primară.
- `@GeneratedValue(strategy = IDENTITY)` — baza de date generează id-ul (auto_increment). Îl citești după `save`.
- **Constructor fără argumente** — Hibernate creează obiectul gol prin reflecție și apoi îi pune câmpurile. Îl poți face `protected` ca să nu-l folosească cineva din greșeală.
- Numele tabelului = numele clasei (poți schimba cu `@Table(name = "...")`), coloanele = câmpurile (`@Column(name = "...")`).

## 2. Repository-ul — `JpaRepository`

Nu scrii clasă de acces la date. Declari o **interfață** și Spring generează implementarea la runtime.

```java
public interface ProductRepository extends JpaRepository<Product, Long> { }
```

`JpaRepository<Product, Long>` = entitatea `Product`, cheia de tip `Long`. Primești gratis:

| metodă | ce face |
|---|---|
| `save(entity)` | insert sau update; întoarce entitatea salvată (cu id) |
| `findById(id)` | `Optional<Product>` |
| `findAll()` | toate rândurile |
| `count()` | câte rânduri sunt |
| `existsById(id)` | boolean |
| `deleteById(id)` / `delete(entity)` | șterge |
| `saveAll(list)` | salvează în bloc |

## 3. Derived queries — Spring citește numele metodei

Declari o metodă **abstractă** cu un nume după o convenție, iar Spring îi generează SQL-ul.

```java
List<Task> findByDone(boolean done);
List<Task> findByPriorityGreaterThan(int priority);
List<Task> findByTitleContainingIgnoreCase(String fragment);
long        countByActiveTrue();
boolean     existsByEmail(String email);
List<Employee> findByDepartmentOrderBySalaryDesc(String department);
```

Vocabular util în numele metodelor:

| cuvânt cheie | efect |
|---|---|
| `findBy` / `countBy` / `existsBy` / `deleteBy` | ce fel de operație |
| `And` / `Or` | combină condiții |
| `GreaterThan` / `LessThanEqual` / `Between` | comparații |
| `Containing` / `StartingWith` | `LIKE` |
| `IgnoreCase` | ignoră majusculele |
| `True` / `False` | condiție pe boolean |
| `OrderBy...Asc` / `OrderBy...Desc` | sortare |

Poți naviga și relații: `findByAuthorName` traversează `Book -> author -> name`.

## 4. `@Query` — când numele nu-ți ajunge

Scrii JPQL (seamănă cu SQL, dar operează pe **entități și câmpuri**, nu pe tabele și coloane).

```java
@Query("select m from Movie m where m.genre = :genre and m.rating >= :minRating order by m.rating desc")
List<Movie> findGoodMoviesByGenre(@Param("genre") String genre, @Param("minRating") double minRating);

@Query("select avg(m.rating) from Movie m where m.genre = :genre")
Double averageRatingForGenre(@Param("genre") String genre);
```

- În JPQL scrii `Movie m` (clasa), nu `movie` (tabelul).
- Legi parametrii cu `:nume` în query și `@Param("nume")` în semnătură.
- Merg funcțiile de agregare: `count`, `avg`, `sum`, `min`, `max`.

## 5. Paginare și sortare

Nu aduci 10.000 de rânduri deodată. Ceri o **pagină**.

```java
Page<Article> findByCategory(String category, Pageable pageable);
```

```java
Page<Article> p = repo.findByCategory("java", PageRequest.of(0, 20, Sort.by("views").descending()));
p.getContent();          // cele 20 de pe pagina 0
p.getTotalElements();    // câte sunt în total
p.getTotalPages();
p.hasNext();
```

Sortarea vine din `Pageable` (`Sort`), **nu** din numele metodei — deci aceeași metodă poate fi sortată oricum.

## 6. Relații între entități

Exemplu 1—* (un autor are mai multe cărți):

```java
@Entity
public class Author {
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
}

@Entity
public class Book {
    @ManyToOne
    private Author author;
}
```

- **Owning side** = partea cu cheia străină în DB. Aici `Book` (are coloana `author_id`) → pune `@ManyToOne`.
- **Inverse side** = `Author`, care doar oglindește relația → `@OneToMany(mappedBy = "author")`. `mappedBy` spune „cheia străină e ținută de câmpul `author` din Book, nu duplica coloana”.
- `cascade = ALL` — când salvezi/ștergi autorul, se propagă la cărți.
- `orphanRemoval = true` — o carte scoasă din listă e ștearsă din DB.
- Ține relația sincronizată din ambele părți (metoda `addBook` setează și `book.setAuthor(this)`).

> Toate tipurile de relații (`@OneToOne`, `@ManyToMany`), best practices (owning vs inverse, cascade, fetch, N+1) și **moștenirea** (`@Inheritance`, `@MappedSuperclass`) sunt tratate în [`RELATII-SI-MOSTENIRE.md`](./RELATII-SI-MOSTENIRE.md).

## 7. De ce H2 la teste și MySQL la aplicație

- Aplicația (`spring-boot:run`) folosește **MySQL** — baza reală, cu date care persistă.
- Testele folosesc `@DataJpaTest`, care pornește un context minim (doar stratul JPA) pe o bază **H2 in-memory**, face fiecare test într-o tranzacție și dă **rollback** la final. Rezultat: teste rapide, izolate, care nu-ți murdăresc baza și nu au nevoie de MySQL pornit.
- Codul (entități, repository-uri) e **identic** — doar dialectul DB diferă, iar JPA îl abstractizează. De asta folosim JPQL portabil, nu SQL specific MySQL.

## 8. `ddl-auto` — cine face tabelele

`spring.jpa.hibernate.ddl-auto` controlează schema:

| valoare | efect |
|---|---|
| `create-drop` | creează tabelele la pornire, le șterge la oprire (bun pentru demo/teste) |
| `update` | ajustează schema fără să șteargă date (comod în dev) |
| `validate` | verifică doar că schema există (aproape de prod) |
| `none` | nu atinge schema (prod — migrezi cu Flyway/Liquibase) |

În lecția asta: `create-drop` la demo, `update` la practice/challenge. **În producție nu folosești `update`.**
