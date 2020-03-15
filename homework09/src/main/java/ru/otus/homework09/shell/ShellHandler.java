package ru.otus.homework09.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework09.domain.Comment;
import ru.otus.homework09.service.BookService;
import ru.otus.homework09.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class ShellHandler {
    private final BookService bookService;
    private final CommentService commentService;

    @ShellMethod(value = "Show all books", key = {"all"})
    public void showAllBooks() {
        bookService.findAllBooks().forEach(System.out::println);
    }

    @ShellMethod(value = "Show book", key = {"show"})
    public void showBook(@ShellOption("title") String title){
       System.out.println(bookService.findBook(title).toString());
    }

    @ShellMethod(value = "Add book", key = {"add"})
    public void addBook(@ShellOption("title") String title, @ShellOption("author") String author, @ShellOption(value = "genre") String genre) {
        bookService.addBook(title, author, genre);
    }

    @ShellMethod(value = "Update book", key = {"update"})
    @SneakyThrows
    public void updateBook(@ShellOption("id") long id, @ShellOption("title") String title, @ShellOption("author") String author, @ShellOption(value = "genre") String genre) {
        bookService.updateBook(id, title, author, genre);
    }

    @ShellMethod(value = "Delete book", key = {"delete"})
    public void deleteBook(@ShellOption("id") long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Add comment", key = {"comment"})
    @SneakyThrows
    public void updateBook(@ShellOption("book_id") long bookId, @ShellOption("content") String content) {
        commentService.add(bookId, content);
    }

    @ShellMethod(value = "Show comments", key = {"show_comments"})
    @SneakyThrows
    public void updateBook(@ShellOption("book_id") long bookId) {
        for (Comment comment : commentService.show(bookId)){
            System.out.println(comment.getContent());
        }

    }

}
