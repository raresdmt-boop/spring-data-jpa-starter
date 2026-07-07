# Greșeli comune — Spring Data JPA

## 1. Ai uitat `@Entity`

```
IllegalArgumentException: Not a managed type: class com.example.jpa.ex01.Product
```
Clasa n-are `@Entity`. Fără ea, JPA n-o cunoaște și repository-ul nu poate porni.

## 2. Ai uitat constructorul fără argumente

```
org.hibernate.InstantiationException: No default constructor for entity
```
Hibernate creează entitatea goală prin reflecție. Adaugă un constructor `protected NumeEntitate() {}` pe lângă cel cu parametri.

## 3. Nume de câmp = cuvânt rezervat SQL

```
Syntax error in SQL statement ... expected "identifier"
```
Câmpuri ca `year`, `order`, `group`, `key` devin coloane rezervate. Redenumește câmpul (`releaseYear`) sau mapează explicit: `@Column(name = "release_year")`.

## 4. Nume de derived query greșit

```
PropertyReferenceException: No property 'nme' found for type 'Employee'
```
Spring parsează numele metodei literă cu literă. `findByNme` nu se potrivește cu niciun câmp. Numele de după `findBy` trebuie să fie exact numele câmpului (cu majusculă): câmp `lastName` → `findByLastName`.

## 5. `@Query` cu tabel în loc de entitate

```
QuerySyntaxException: Book is not mapped
```
În JPQL scrii **numele clasei**, nu al tabelului: `select b from Book b`, nu `select * from book`. Și folosești câmpuri (`b.price`), nu coloane.

## 6. `@Param` lipsă sau nume greșit

```
Using named parameters ... but no @Param
```
Fiecare `:nume` din `@Query` trebuie legat de un `@Param("nume")` pe parametrul corespunzător, cu exact același nume.

## 7. `mappedBy` pus greșit → două coloane de FK

Dacă pui `@OneToMany` fără `mappedBy`, Hibernate crede că e o relație separată și face un tabel de legătură / o coloană în plus. `mappedBy = "author"` spune „relația e deja ținută de câmpul `author` din partea cealaltă”. Owning side (`@ManyToOne`) nu are `mappedBy`.

## 8. Relație nesincronizată din ambele părți

Dacă faci doar `author.getBooks().add(book)` dar nu setezi `book.setAuthor(author)`, coloana `author_id` rămâne `null` în DB, deși în memorie pare legat. Ține o metodă helper (`addBook`) care setează ambele capete.

## 9. `LazyInitializationException`

```
failed to lazily initialize a collection ... no Session
```
`@OneToMany` e implicit **LAZY**: colecția se încarcă doar când o accesezi, cât timp sesiunea e deschisă. Dacă o accesezi după ce tranzacția s-a închis, crapă. Soluții: accesează colecția în interiorul tranzacției, sau folosește un `@Query ... join fetch`, sau `findAll` cu entity graph. (În teste cu `@DataJpaTest` ești în tranzacție, deci nu apare de obicei.)

## 10. Crezi că `findAll()` fără paginare e ok

Pe un tabel mare, `findAll()` aduce tot în memorie. Când ai mult de citit, folosește `Page<T>` + `Pageable`.

## 11. `ddl-auto: update` în producție

`update` nu șterge date, dar face modificări de schemă imprevizibile și nu poate face tot (rename, drop). În prod: `validate` + migrări versionate (Flyway/Liquibase).

## 12. Nu suprascrii `equals`/`hashCode` pe baza id-ului auto-generat

Un id `null` înainte de `save` și non-null după strică `HashSet`-urile. Pentru început: nu pune entitățile în `Set` bazat pe id generat, sau folosește o cheie de business stabilă. (Subiect avansat — reține doar că e o capcană.)
