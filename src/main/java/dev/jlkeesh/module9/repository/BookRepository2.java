package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository2 extends AbstractRepository<Book, Long> {

    public BookRepository2(EntityManager em) {
        super(em);
    }
}
