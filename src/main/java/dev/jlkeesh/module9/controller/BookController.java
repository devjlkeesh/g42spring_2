package dev.jlkeesh.module9.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dev.jlkeesh.module9.PostDto;
import dev.jlkeesh.module9.entity.Book;
import dev.jlkeesh.module9.repository.BookRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import java.net.URI;
import java.net.URL;
import java.util.List;


@Controller
@ResponseBody
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;
    private final ObjectMapper jacksonObjectMapper;

    public BookController(BookRepository bookRepository, ObjectMapper jacksonObjectMapper) {
        this.bookRepository = bookRepository;
        this.jacksonObjectMapper = jacksonObjectMapper;
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

    @GetMapping(value = "/jsonnode", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    @SneakyThrows
    public List<PostDto> jsonNodeExample(HttpServletResponse response) {
/*        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();*/
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        return jacksonObjectMapper.readValue(url, new TypeReference<>() {
        });

/*
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        JsonGenerator jsonGenerator = jacksonObjectMapper.getFactory()
                .createGenerator(response.getOutputStream());
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("firstName", "Sardor");
        jsonGenerator.writeStringField("lastName", "Elmurodov");
        jsonGenerator.writeArrayFieldStart("books");
        jsonGenerator.writeString("Complete Reference");
        jsonGenerator.writeString("Modern JAva In Action");
        jsonGenerator.writeNumber(3213123);
        jsonGenerator.writeEndArray();
//        jsonGenerator.writeEndObject();
        jsonGenerator.close();*/
    }

    @GetMapping(value = "/jackson", produces = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public String findAllJackson(@RequestParam(required = false) String author) {
        if (author == null) {
            return jacksonObjectMapper.writeValueAsString(bookRepository.findAll());
        }
        return jacksonObjectMapper.writeValueAsString(bookRepository.findAllByAuthor(author));
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
