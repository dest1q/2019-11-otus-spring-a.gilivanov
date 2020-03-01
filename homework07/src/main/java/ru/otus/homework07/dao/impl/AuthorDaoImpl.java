package ru.otus.homework07.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework07.dao.AuthorDao;
import ru.otus.homework07.domain.Author;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;


    @Override
    public Author findById(int id) throws NoDataFoundException {
        Author author;
        try {
            author = jdbc.queryForObject("select id, full_name from authors where id = :id", Collections.singletonMap("id", id), new AuthorRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException(e.getMessage());
        }
        return author;
    }

    @Override
    public Author findByName(String name) throws NoDataFoundException {
        Author author;
        try {
            author = jdbc.queryForObject("select id, full_name from authors where full_name = :full_name", Collections.singletonMap("full_name", name), new AuthorRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException(e.getMessage());
        }
        return author;
    }

    @Override
    public List<Author> findAll() {
        return jdbc.query("select id, full_name from authors", new AuthorRowMapper());
    }

    @Override
    public Author add(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("full_name", author.getFullName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into authors(full_name) values(:full_name)", params, keyHolder);
        author.setId(keyHolder.getKey().intValue());
        return author;
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", author.getId()).addValue("full_name", author.getFullName());
        jdbc.update("update authors set full_name = :full_name where id = :id", params);
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String fullName = rs.getString("full_name");
            return new Author(id, fullName);
        }
    }
}
