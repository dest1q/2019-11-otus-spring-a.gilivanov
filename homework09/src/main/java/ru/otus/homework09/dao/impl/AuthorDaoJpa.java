package ru.otus.homework09.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework09.dao.AuthorDao;
import ru.otus.homework09.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthorDaoJpa implements AuthorDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> q = entityManager.createQuery("select a from Author a where a.fullName = :name", Author.class);
        q.setParameter("name", name);
        try {
            return Optional.ofNullable(q.getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Author save(Author author){
        if (author.getId() != 0){
            return entityManager.merge(author);
        }
        else {
            entityManager.persist(author);
            return author;
        }
    }
}
