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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> indexBook() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("Select Person.* from book JOIN person ON book.person_id = person.id WHERE book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(),
                book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id=?", person.getId(), id);
    }
}
