# exercitii-rezolvate — demo runnable

Cod **rezolvat**, de citit și de rulat, ca să vezi conceptele lucrând concret. Domeniu: un catalog de cărți.

## Rulează demo-ul pe MySQL

```bash
docker compose up -d                              # din rădăcina proiectului
./mvnw -pl exercitii-rezolvate spring-boot:run    # afișează CRUD, derived, @Query, paginare
```

`CatalogRunner` inserează câteva cărți și tipărește în consolă rezultatele fiecărui tip de interogare.

## Rulează testele rezolvate (pe H2, fără MySQL)

```bash
./mvnw -pl exercitii-rezolvate test               # Tests run: 6, verzi
```

## Ce să te uiți în cod

| fișier | ce arată |
|---|---|
| `Book.java` | o entitate completă |
| `BookRepository.java` | derived queries, `@Query`, agregare, paginare — toate într-un loc |
| `CatalogRunner.java` | cum apelezi repository-ul dintr-un bean |
| `BookRepositoryTest.java` | cum testezi un repository cu `@DataJpaTest` |
