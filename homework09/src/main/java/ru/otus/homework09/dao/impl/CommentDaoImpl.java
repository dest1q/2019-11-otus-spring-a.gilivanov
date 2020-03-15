package ru.otus.homework09.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework09.dao.CommentDao;
import ru.otus.homework09.domain.Book;
import ru.otus.homework09.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Comment add(Book book, String content) {
        Comment comment = new Comment(book, content);
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public List<Comment> getComments(Book book) {
        TypedQuery<Comment> query = entityManager.createQuery("select a from Comment a join a.book b where b.id = :book_id", Comment.class);
        query.setParameter("book_id", book.getId());
        return query.getResultList();
    }
}
