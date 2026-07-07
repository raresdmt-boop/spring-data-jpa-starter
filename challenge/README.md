# challenge — Librărie (capstone)

Integrezi tot din lecție într-un singur domeniu: o librărie unde o `Category` are mai multe `Book`.

```
Category 1 ────< * Book
```

Rezolvi în [`starter/`](./starter/). Cele 4 teste de acceptanță din `BookstoreAcceptanceTest` sunt **roșii** până termini. Rulezi pe H2.

```bash
./mvnw -pl challenge/starter test
```

## Ce ai de făcut (5 TODO-uri)

| TODO | fișier | concept |
|---|---|---|
| challenge.1 | `Category.java` | `@OneToMany(mappedBy = "category", cascade = ALL, orphanRemoval = true)` |
| challenge.2 | `Book.java` | `@ManyToOne` pe `category` (owning side) |
| challenge.3 | `BookRepository.java` | derived query peste relație: `findByCategoryName` |
| challenge.4 | `BookRepository.java` | paginare: `findByPriceLessThanEqual(double, Pageable)` → `Page<Book>` |
| challenge.5 | `BookRepository.java` | `@Query` JPQL: cărțile dintr-o categorie, cel mai scump primul |

## Ce verifică testele de acceptanță

- salvarea unei categorii cu 3 cărți face cascadă (cărțile primesc id);
- `findByCategoryName` navighează relația și întoarce cărțile corecte;
- paginarea întoarce felia cerută, cu total corect;
- `@Query`-ul ordonează descrescător după preț.

## Definition of Done

```bash
./mvnw -pl challenge/starter test    # Tests run: 4, Failures: 0, Errors: 0
```
