package ru.otus.homework09.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    public Book(String title, Author author, Genre genre){
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
