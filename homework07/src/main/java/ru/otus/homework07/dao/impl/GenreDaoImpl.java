package ru.otus.homework07.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework07.dao.GenreDao;
import ru.otus.homework07.domain.Genre;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre findById(int id) throws NoDataFoundException {
        Genre genre;
        try {
            genre = jdbc.queryForObject("select id, name from genres where id = :id", Collections.singletonMap("id", id), new GenreRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException(e.getMessage());
        }
        return genre;
    }

    @Override
    public Genre findByName(String name) throws NoDataFoundException {
        Genre genre;
        try {
            genre = jdbc.queryForObject("select id, name from genres where name = :name", Collections.singletonMap("name", name), new GenreRowMapper());
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException(e.getMessage());
        }
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("select id, full_name from genres", new GenreRowMapper());
    }

    @Override
    public Genre add(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genres(name) values(:name)", params, keyHolder);
        genre.setId(keyHolder.getKey().intValue());
        return genre;
    }

    @Override
    public void update(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", genre.getId()).addValue("name", genre.getName());
        jdbc.update("update genres set name = :name where id = :id", params);
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
