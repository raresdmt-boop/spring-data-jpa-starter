# practice — 6 exerciții Spring Data JPA

Fiecare exercițiu e un pachet `ex0N` cu o entitate + un repository + un test. Testul e **roșu** până rezolvi TODO-urile din cod. Rulezi pe H2 — **nu-ți trebuie MySQL pornit**.

## Cum lucrezi

```bash
./mvnw -pl practice test -Dtest=Ex01ProductRepositoryTest   # un exercițiu
./mvnw -pl practice test                                    # toate
```

Nu modifici testele. Rezolvi doar clasele din `src/main`. Fiecare `// TODO exNN` îți spune exact ce ai de făcut.

## Exercițiile

| # | pachet | concept | ce ai de făcut |
|---|---|---|---|
| ex01 | `ex01` | `@Entity` / `@Id` / `@GeneratedValue` | mapează POJO-ul `Product` ca entitate |
| ex02 | `ex02` | derived queries simple | transformă stub-urile `default` în metode derivate (`findByDone`, `...GreaterThan`, `...ContainingIgnoreCase`) |
| ex03 | `ex03` | derived queries avansate | `...OrderBy...Desc`, `countBy...True`, `existsBy...`, `...Containing...` |
| ex04 | `ex04` | `@Query` (JPQL) | scrie JPQL cu `@Param`, inclusiv o agregare `avg(...)` |
| ex05 | `ex05` | paginare + sortare | metodă derivată cu `Pageable` care întoarce `Page<Article>` |
| ex06 | `ex06` | relații `@OneToMany` / `@ManyToOne` | mapează relația `Author` 1—* `Book` + un derived query peste relație |

## Tiparul „default → abstract”

La derived queries și `@Query`, stub-ul e o metodă `default` care aruncă `UnsupportedOperationException`. Asta doar ca proiectul să compileze. Tu:

1. ștergi cuvântul `default` și corpul `{ ... }`,
2. (la `@Query`) adaugi adnotarea deasupra,
3. lași **doar semnătura abstractă**.

Spring Data generează implementarea din numele metodei / din JPQL-ul tău.

## Definition of Done

```bash
./mvnw -pl practice test     # Tests run: 16, Failures: 0, Errors: 0
```
