# Provocări — fără hinturi

10 exerciții fără indicații despre ce adnotare sau ce metodă să folosești. Cerința e doar **funcțională**: ce trebuie să facă codul. Testele sunt sursa de adevăr — dacă trec testele, e bine.

**Cum lucrezi:** fiecare provocare are 1-3 clase scheleton și un test. Modifică **doar fișierele din `main/`**. NU modifica testele. Rulează:

```bash
./mvnw -pl practice test -Dtest='C01*'   # o provocare
./mvnw -pl practice test -Dtest='C*'     # toate
```

---

## c01 — Book (mapare)

`BookRepository.save(new Book("Ion", 400))` trebuie să persiste cartea și să-i genereze un `id`. `findById` întoarce cartea cu titlul și numărul de pagini corecte.

## c02 — Phone (două condiții)

`findByBrandAndPriceLessThan(brand, maxPrice)` întoarce telefoanele de brand-ul dat cu prețul strict sub `maxPrice`.

## c03 — Post (căutare în text)

`searchByKeyword(kw)` întoarce postările al căror titlu conține `kw` oriunde, indiferent de majuscule.

## c04 — Remark (paginare)

`findByLikesGreaterThan(minLikes, pageable)` întoarce, paginat, remarcile cu mai multe like-uri decât `minLikes`. Testul cere pagina a doua, sortată descrescător după like-uri.

## c05 — Customer 1—* Purchase

Un `Customer` are mai multe `Purchase`. Salvarea clientului trebuie să salveze și cumpărăturile (cascadă). `PurchaseRepository.findByCustomerName(name)` întoarce cumpărăturile unui client, iar din fiecare `Purchase` trebuie să poți ajunge la `Customer`.

## c06 — Student (potrivire + sortare)

`findByNameContainingIgnoreCaseOrderByAgeAsc(fragment)` întoarce studenții al căror nume conține `fragment` (case-insensitive), ordonați crescător după vârstă.

## c07 — Worker (agregare)

`averageSalary(department)` întoarce salariul mediu dintr-un departament.

## c08 — Alert (primul din ordine)

`findFirstByOrderByPriorityDesc()` întoarce alerta cu prioritatea cea mai mare.

## c09 — Product (prefix)

`findByCodeStartingWith(prefix)` întoarce produsele al căror cod începe cu `prefix`.

## c10 — Team 1—* Athlete (orphan removal)

Un `Team` are mai mulți `Athlete`. Când scoți un atlet din echipă și salvezi echipa, atletul trebuie **șters din baza de date** (nu doar dezlegat). După ce elimini un atlet dintr-o echipă cu 2 atleți, în DB rămâne 1.
