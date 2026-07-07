# practice — 29 exerciții Spring Data JPA

Trei niveluri, ca la fundamentals: `ex01-09` ghidate (TODO în cod) → `practica/pNN` cu cerință funcțională → `provocari/cNN` fără niciun hint (cerința e în [`provocari/README.md`](./src/main/java/com/example/jpa/provocari/README.md)). Toate testele sunt **roșii** până le rezolvi. Rulezi pe H2 — **nu-ți trebuie MySQL pornit**.

## Cum lucrezi

```bash
./mvnw -pl practice test -Dtest=Ex01ProductRepositoryTest   # un exercițiu
./mvnw -pl practice test -Dtest='P*'                        # toate practica
./mvnw -pl practice test -Dtest='C*'                        # toate provocările
./mvnw -pl practice test                                    # tot modulul
```

Nu modifici testele. Rezolvi doar clasele din `src/main`.

## Exerciții ghidate (ex01-09)

| # | concept | ce ai de făcut |
|---|---|---|
| ex01 | `@Entity` / `@Id` / `@GeneratedValue` | mapează POJO-ul `Product` ca entitate |
| ex02 | derived queries simple | `findByDone`, `...GreaterThan`, `...ContainingIgnoreCase` |
| ex03 | derived queries avansate | `...OrderBy...Desc`, `countBy...True`, `existsBy...`, `...Containing...` |
| ex04 | `@Query` (JPQL) | JPQL cu `@Param`, inclusiv agregare `avg(...)` |
| ex05 | paginare + sortare | metodă derivată cu `Pageable` → `Page<Article>` |
| ex06 | relație `@OneToMany` / `@ManyToOne` (1—*) | `Author` 1—* `Book` + derived query peste relație |
| ex07 | relație `@OneToOne` (1—1) | `Citizen` 1—1 `Passport`, owning side cu `@JoinColumn` |
| ex08 | relație `@ManyToMany` (*—*) | `Actor` ↔ `Film`, `@JoinTable` + `mappedBy`, `Set` |
| ex09 | moștenire `@Inheritance` | `Payment` → `CardPayment` / `CashPayment`, SINGLE_TABLE + discriminator |

## practica/ (p01-p10) și provocari/ (c01-c10)

- `practica/pNN` — fiecare repository are o metodă `default` cu o **cerință funcțională** în comentariu. Concepte: derived queries variate (`Between`, `In`, `Top`, `First`, `deleteBy`), `@Query`, agregări, paginare, `Sort`.
- `provocari/cNN` — **fără hinturi**: doar cerința din [`provocari/README.md`](./src/main/java/com/example/jpa/provocari/README.md). Acoperă și relații (`@ManyToOne`, `@OneToMany` cu orphan removal) și mapare de la zero.

> Citește întâi [`teorie/RELATII-SI-MOSTENIRE.md`](../teorie/RELATII-SI-MOSTENIRE.md) pentru ex06-09.

## Tiparul „default → abstract”

La derived queries și `@Query`, stub-ul e o metodă `default` care aruncă `UnsupportedOperationException`. Asta doar ca proiectul să compileze. Tu:

1. ștergi cuvântul `default` și corpul `{ ... }`,
2. (la `@Query`) adaugi adnotarea deasupra,
3. lași **doar semnătura abstractă**.

Spring Data generează implementarea din numele metodei / din JPQL-ul tău.

## Definition of Done

```bash
./mvnw -pl practice test     # Tests run: 46, Failures: 0, Errors: 0
```
