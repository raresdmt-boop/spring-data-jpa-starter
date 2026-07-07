# Exerciții de teorie — Spring Data JPA

Răspunde cu cuvintele tale (nu copia din THEORY.md). Sunt întrebări de înțelegere, nu de cod.

## Mapare

1. Ce face `@Entity` și ce eroare apare fără ea?
2. De ce are nevoie o entitate de un constructor fără argumente? Cine îl folosește?
3. Ce diferență e între `GenerationType.IDENTITY` și a-ți seta tu id-ul manual?
4. Când ai nevoie de `@Column(name = ...)` sau `@Table(name = ...)`?

## Repository

5. Ce câștigi când extinzi `JpaRepository` în loc să scrii singur SQL/JDBC?
6. `save()` face insert sau update? De unde știe Hibernate care din ele?
7. De ce `findById` întoarce `Optional<T>` și nu direct `T`?

## Derived queries

8. Cum „știe” Spring ce SQL să genereze pentru `findByDepartmentOrderBySalaryDesc`?
9. Scrie (pe hârtie) numele metodei derivate pentru: „angajații activi dintr-un departament, ordonați crescător după nume”.
10. Ce se întâmplă dacă greșești numele câmpului în numele metodei?

## @Query

11. Când alegi `@Query` în loc de un derived query?
12. Ce diferență e între JPQL și SQL? Dă un exemplu concret din lecție.
13. La ce folosește `@Param` și ce trebuie să se potrivească exact?

## Paginare

14. De ce e paginarea importantă pe tabele mari?
15. De unde vine sortarea la o metodă cu `Pageable` — din numele metodei sau din altă parte?
16. Ce-ți spune `page.getTotalElements()` față de `page.getContent().size()`?

## Relații

17. Ce e owning side și inverse side? Care primește `@ManyToOne` și care `@OneToMany`?
18. Ce face `mappedBy` și ce se întâmplă dacă îl uiți?
19. La ce folosește `cascade = CascadeType.ALL`? Dar `orphanRemoval = true`?
20. De ce e important ca metoda `addBook` să seteze relația în ambele capete?

## Infrastructură

21. De ce merg testele pe H2 dar aplicația pe MySQL, deși codul e același?
22. Ce face `@DataJpaTest` diferit de `@SpringBootTest`?
23. Ce înseamnă fiecare valoare de `ddl-auto` și de ce nu folosești `update` în producție?
