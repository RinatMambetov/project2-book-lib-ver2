package ru.rinat.bookLibVer2.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rinat.bookLibVer2.models.Book;
import ru.rinat.bookLibVer2.models.Person;
import ru.rinat.bookLibVer2.repositories.PersonsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonsService {
    private final PersonsRepository personsRepository;

    @Autowired
    public PersonsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public List<Person> findAll() {
        return personsRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = personsRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void save(Person person) {
        personsRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        personsRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personsRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return findById(id).getBooks();
    }

    public List<Person> findByFullName(String fullName) {
        return personsRepository.findByFullName(fullName);
    }
}
