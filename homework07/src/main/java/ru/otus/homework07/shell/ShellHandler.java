package ru.otus.homework07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework07.exceptions.NoDataFoundException;
import ru.otus.homework07.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class ShellHandler {
    private final BookService bookService;

    @ShellMethod(value = "Show all books", key = {"all"})
    public void showAllBooks() {
        bookService.findAllBooks().forEach(System.out::println);
    }

    @ShellMethod(value = "Show book", key = {"show"})
    public void showBook(@ShellOption("title") String title){
        try {
            System.out.println(bookService.findBook(title).toString());
        } catch (NoDataFoundException e) {
            System.out.println("Book not found");
        }
    }

    @ShellMethod(value = "Add book", key = {"add"})
    public void addBook(@ShellOption("title") String title, @ShellOption("author") String author, @ShellOption(value = "genre") String genre) {
        bookService.addBook(title, author, genre);
    }

    @ShellMethod(value = "Update book", key = {"update"})
    public void updateBook(@ShellOption("id") int id, @ShellOption("title") String title, @ShellOption("author") String author, @ShellOption(value = "genre") String genre) {
        bookService.updateBook(id, title, author, genre);
    }

    @ShellMethod(value = "Delete book", key = {"delete"})
    public void deleteBook(@ShellOption("id") int id) {
        bookService.deleteBook(id);
    }

}
