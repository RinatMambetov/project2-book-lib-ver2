package ru.rinat.bookLibVer2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rinat.bookLibVer2.models.Person;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFullName(String fullName);
}
