# exercitii-rezolvate — demo runnable

Cod **rezolvat**, de citit și de rulat, ca să vezi fiecare concept lucrând concret și să-i vezi datele în MySQL. Fiecare exercițiu e independent: îl pornești singur cu un argument și îi inspectezi tabelele în baza de date.

## Pornește baza de date (o singură dată)

```bash
docker compose up -d          # din rădăcina proiectului, MySQL pe localhost:3306
```

## Rulează UN exercițiu

```bash
./mvnw -pl exercitii-rezolvate spring-boot:run -Dspring-boot.run.arguments=<cheie>
```

Fără argument, aplicația îți afișează meniul cu toate cheile disponibile.

| cheie | concept | ce vezi în DB |
|---|---|---|
| `catalog` | `@Entity` + derived queries + `@Query` + paginare | `SELECT * FROM book;` |
| `derivate` | derived queries (`OrderBy`, `GreaterThan`, `Containing`, `Top`, `countBy`, `existsBy`) | `SELECT * FROM employee;` |
| `unulamulti` | `@OneToMany` / `@ManyToOne` (1—*) | `team`, apoi `player` (coloana FK `team_id`) |
| `unulaunu` | `@OneToOne` (1—1) | `citizen` (FK `passport_id`), apoi `passport` |
| `multilamulti` | `@ManyToMany` (*—*) | `actor`, `film`, `actor_film` (tabelul de legătură) |
| `mostenire` | `@Inheritance` SINGLE_TABLE + discriminator | `SELECT payment_type, amount, card_number FROM payment;` |

Exemplu:

```bash
./mvnw -pl exercitii-rezolvate spring-boot:run -Dspring-boot.run.arguments=multilamulti
```

Fiecare exercițiu golește tabelele lui, inserează niște date, rulează interogările și tipărește rezultatul; la final îți spune exact ce `SELECT` să dai în MySQL ca să vezi rândurile. `show-sql: true` — vezi și SQL-ul pe care îl generează Hibernate în consolă.

## Relații și sesiune

Demo-urile cu relații (`unulamulti`, `multilamulti`, `unulaunu`) rulează metoda într-o tranzacție (`@Transactional`), ca sesiunea să rămână deschisă cât navighezi colecțiile **LAZY** (`@OneToMany` și `@ManyToMany` sunt LAZY implicit). Fără tranzacție, `colectie.getX()` după închiderea sesiunii dă `LazyInitializationException`.

## Cum se leagă de `practice/`

Domeniile sunt alese să oglindească exercițiile pe care le ai de rezolvat tu:

| aici (rezolvat) | acolo (de rezolvat) |
|---|---|
| `derivate` (Employee) | `practice` ex02, ex03 |
| `unulamulti` (Team/Player) | `practice` ex06 (Author/Book) |
| `unulaunu` (Citizen/Passport) | `practice` ex07 |
| `multilamulti` (Actor/Film) | `practice` ex08 |
| `mostenire` (Payment) | `practice` ex09 |

## Rulează testele (pe H2, fără MySQL)

```bash
./mvnw -pl exercitii-rezolvate test
```

`BookRepositoryTest` — cum testezi un repository cu `@DataJpaTest`.
`AllExercisesSmokeTest` — pornește tot contextul și rulează fiecare exercițiu, ca plasă de siguranță.

## Ce să te uiți în cod

| fișier | ce arată |
|---|---|
| `Book.java` + `BookRepository.java` | o entitate completă cu derived queries, `@Query`, agregare, paginare |
| `DemoExercise.java` | interfața pe care o implementează fiecare exercițiu |
| `DemoRunner.java` | cum alege dispatcher-ul exercițiul din argument |
| `unulamulti/`, `unulaunu/`, `multilamulti/` | cele trei tipuri de relații, cu owning side / `mappedBy` |
| `mostenire/` | moștenire SINGLE_TABLE cu discriminator |
