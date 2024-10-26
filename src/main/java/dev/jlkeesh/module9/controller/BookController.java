package dev.jlkeesh.module9.controller;

import dev.jlkeesh.module9.entity.Book;
import dev.jlkeesh.module9.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@Controller
@ResponseBody
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
//        return ResponseEntity.status(201).body(savedBook);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        return savedBook;
    }

    @PutMapping
    public Book update(@RequestBody Book dto) {
        Book book = find(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> findAll(@RequestParam(required = false) String author) {
        if (author == null) {
            return bookRepository.findAll();
        }
        return bookRepository.findAllByAuthor(author);
    }

    @GetMapping("/{id}")
    public Book find(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookRepository.delete(id);
    }
}
