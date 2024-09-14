package ru.rinat.bookLibVer2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rinat.bookLibVer2.models.Book;
import ru.rinat.bookLibVer2.models.Person;
import ru.rinat.bookLibVer2.repositories.BooksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book oldBook = findById(id);
        Person person = oldBook.getPerson();
        book.setId(id);
        book.setPerson(person);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getPerson(int id) {
        return Optional.ofNullable(findById(id).getPerson());
    }

    @Transactional
    public void release(int id) {
        Book book = findById(id);
        Person person = book.getPerson();
        person.getBooks().remove(book);
        book.setPerson(null);
        booksRepository.save(book);
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = findById(id);
        book.setPerson(person);
        List<Book> books = person.getBooks();
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        update(id, book);
    }
}
