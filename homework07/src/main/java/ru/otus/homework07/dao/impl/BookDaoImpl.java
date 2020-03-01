package ru.otus.homework07.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework07.dao.BookDao;
import ru.otus.homework07.domain.Author;
import ru.otus.homework07.domain.Book;
import ru.otus.homework07.domain.Genre;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book findById(int id) throws NoDataFoundException {
        Book book;
        try {
            book = jdbc.queryForObject("select b.id, b.title, a.id as author_id, a.full_name as author_name, g.id as genre_id, g.name as genre_name " +
                                            "from books b " +
                                            "join authors a on a.id = b.author_id " +
                                            "join genres g on g.id = b.genre_id " +
                                            "where b.id = :id", Collections.singletonMap("id", id), new BookRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException(e.getMessage());
        }
        return book;
    }

    @Override
    public Book findByName(String name) throws NoDataFoundException {
        Book book;
        try {
            book = jdbc.queryForObject("select b.id, b.title, a.id as author_id, a.full_name as author_name, g.id as genre_id, g.name as genre_name " +
                    "from books b " +
                    "join authors a on a.id = b.author_id " +
                    "join genres g on g.id = b.genre_id " +
                    "where b.title = :title", Collections.singletonMap("title", name), new BookRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException(e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query("select b.id, b.title, a.id as author_id, a.full_name as author_name, g.id as genre_id, g.name as genre_name " +
                "from books b " +
                "join authors a on a.id = b.author_id " +
                "join genres g on g.id = b.genre_id", new BookRowMapper());
    }

    @Override
    public Book add(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into books(title, author_id, genre_id) values(:title, :author_id, :genre_id)", params, keyHolder);
        book.setId(keyHolder.getKey().intValue());
        return book;
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        jdbc.update("update books set title = :title, author_id = :author_id, genre_id = :genre_id where id = :id", params);
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int authorId = rs.getInt("author_id");
            String authorName = rs.getString("author_name");
            int genreId = rs.getInt("genre_id");
            String genreName = rs.getString("genre_name");
            return new Book(id, title, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}
