package ru.otus.homework09.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework09.dao.BookDao;
import ru.otus.homework09.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookDaoJpa implements BookDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Optional<Book> findByName(String name) {
        TypedQuery<Book> q = entityManager.createQuery("select a from Book a join fetch a.author join fetch a.genre where a.title = :name", Book.class);
        q.setParameter("name", name);
        try {
            return Optional.ofNullable(q.getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select a from Book a join fetch a.author join fetch a.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() != 0){
            return entityManager.merge(book);
        }
        else {
            entityManager.persist(book);
            return book;
        }
    }

    @Override
    public void delete(long id) {
        Optional<Book> book = findById(id);
        if (!book.isEmpty()){
            entityManager.remove(book.get());
        }
    }
}
