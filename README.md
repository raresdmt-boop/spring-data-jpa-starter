# Spring Data JPA (Lecția 02)

Mapare `@Entity`, `JpaRepository`, derived queries, `@Query` (JPQL), paginare + sortare, **toate relațiile** (`@OneToMany`/`@ManyToOne`, `@OneToOne`, `@ManyToMany`) cu best practices, și **moștenire** (`@Inheritance`).

Persistență reală pe **MySQL** (via `docker-compose`). Testele rulează pe **H2 in-memory** — rapide și offline, fără să depindă de un MySQL pornit. Proiect Maven **multi-module**; rulezi fiecare modul cu `-pl` din rădăcină.

## Cerințe

```bash
java -version    # 21+ (folosește ./mvnw — nu-ți trebuie Maven instalat)
docker --version # pentru MySQL-ul aplicației
```

## Pornește baza de date (o singură dată)

```bash
docker compose up -d          # MySQL 8 pe localhost:3306, db `jpa_lesson`, user jpa/jpa
docker compose ps             # verifică: healthy
```

## Structură

| modul | rol | teste |
|---|---|---|
| [`teorie/`](./teorie/) | de citit înainte: `THEORY.md`, `RELATII-SI-MOSTENIRE.md`, `GRESELI-COMUNE.md`, `EXERCITII-TEORIE.md` | — |
| [`exercitii-rezolvate/`](./exercitii-rezolvate/) | demo runnable pe MySQL: vezi JPA lucrând (CRUD, derived, `@Query`, paginare) + exemple rezolvate | ✅ verzi |
| [`practice/`](./practice/) | 29 exerciții de făcut tu (ex01-09 ghidate → practica → provocari fără hint) | 🔴 până le rezolvi |
| [`challenge/starter/`](./challenge/) | capstone: librărie `Category` 1—* `Book`, schelet + teste de acceptanță | 🔴 până îl rezolvi |

## Ordinea recomandată

```bash
# 1. citește teoria
open teorie/THEORY.md

# 2. vezi conceptele rulând concret pe MySQL (necesită docker compose up)
./mvnw -pl exercitii-rezolvate spring-boot:run

# 3. fă exercițiile (detalii în practice/README.md) — rulează pe H2, nu-ți trebuie DB
./mvnw -pl practice test

# 4. atacă challenge-ul (detalii în challenge/README.md)
./mvnw -pl challenge/starter test
```

## Comenzi

```bash
./mvnw -pl practice test -Dtest=Ex01ProductRepositoryTest   # un singur exercițiu
./mvnw -pl practice test                                    # toate testele modulului
./mvnw -fae test                                            # tot reactorul (practice+challenge RED — normal)
```

> `RED` la `practice` și `challenge` e starea de pornire — le faci `GREEN` rezolvând exercițiile. Nu modifici testele.

## Concepte cheie

- **`@Entity`** — clasă Java mapată pe un tabel; are nevoie de `@Id` și un constructor fără argumente (fie și `protected`).
- **`JpaRepository<T, ID>`** — interfață pe care o extinzi; Spring îți dă gratis `save`, `findById`, `findAll`, `count`, `delete` etc. Nu scrii nicio implementare.
- **Derived query** — Spring generează SQL-ul din **numele metodei**: `findByGenre`, `findByPriceGreaterThan`, `countByActiveTrue`, `existsByEmail`, `...OrderBySalaryDesc`.
- **`@Query`** — când numele nu-ți ajunge, scrii tu JPQL: `@Query("select b from Book b where b.price <= :max")`.
- **Paginare** — adaugi un parametru `Pageable` și întorci `Page<T>`; sortarea vine din `Pageable` (`Sort`), nu din numele metodei.
- **Relații** — `@ManyToOne`/`@OneToMany` (1—*), `@OneToOne` (1—1), `@ManyToMany` (*—*); owning side ține FK/join-table, inverse side folosește `mappedBy`. Best practices în [`teorie/RELATII-SI-MOSTENIRE.md`](./teorie/RELATII-SI-MOSTENIRE.md).
- **Moștenire** — `@Inheritance` (SINGLE_TABLE / JOINED / TABLE_PER_CLASS) și `@MappedSuperclass`; un repository peste tipul de bază întoarce polimorfic.
- **H2 pentru teste** — `@DataJpaTest` pornește un context minim doar cu stratul JPA, pe o bază H2 in-memory, și face rollback după fiecare test.

## Definition of Done

- `./mvnw -pl practice test` → toate cele 46 de teste GREEN (ex01-09 + practica + provocari).
- `./mvnw -pl challenge/starter test` → cele 4 teste de acceptanță GREEN.
- `./mvnw -pl exercitii-rezolvate spring-boot:run` pornește pe MySQL și afișează catalogul.
- Ai parcurs întrebările din [`teorie/EXERCITII-TEORIE.md`](./teorie/EXERCITII-TEORIE.md).
- Poți explica:
  - de ce o entitate are nevoie de constructor fără argumente;
  - diferența dintre un derived query și un `@Query`;
  - ce e owning side vs. inverse side într-o relație și ce face `mappedBy`;
  - de ce testele merg pe H2 dar aplicația pe MySQL, deși codul e identic.
```
