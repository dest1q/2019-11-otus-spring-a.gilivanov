package ru.otus.homework09.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework09.dao.GenreDao;
import ru.otus.homework09.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class GenreDaoJpa implements GenreDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> findByName(String name) {
        TypedQuery<Genre> q = entityManager.createQuery("select g from Genre g where g.name = :name", Genre.class);
        q.setParameter("name", name);
        try {
            return Optional.ofNullable(q.getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Genre save(Genre genre){
        if (genre.getId() != 0){
            return entityManager.merge(genre);
        }
        else {
            entityManager.persist(genre);
            return genre;
        }
    }

}
