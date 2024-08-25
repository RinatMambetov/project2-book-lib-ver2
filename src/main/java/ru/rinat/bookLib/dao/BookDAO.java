package ru.rinat.bookLib.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rinat.bookLib.models.Book;
import ru.rinat.bookLib.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexByPersonId(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class),
                personId);
    }
}
