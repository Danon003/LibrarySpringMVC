package ru.danon.spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danon.spring.models.Book;
import ru.danon.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
      return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> getPersonByFullName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }


    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, year_birth) VALUES (?, ?)", person.getFullName(), person.getYearBirth());
    }

    public void update(Person updatedPerson, int id) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_birth=? WHERE id=?", updatedPerson.getFullName(),
                updatedPerson.getYearBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
