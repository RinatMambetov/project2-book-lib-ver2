package ru.rinat.bookLib.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rinat.bookLib.models.Book;
import ru.rinat.bookLib.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book newBook) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE id=?",
                newBook.getTitle(), newBook.getAuthor(), newBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Optional<Person> getOwner(int id) {
        return jdbcTemplate.query(
                        "SELECT person.* from person " +
                                "join book on book.person_id=person.id " +
                                "where book.id=?",
                        new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findFirst();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?",
                person.getId(), id);
    }
}
