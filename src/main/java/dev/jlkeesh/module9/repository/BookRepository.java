package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.dto.BookClassProjection;
import dev.jlkeesh.module9.dto.BookInterfaceProjection;
import dev.jlkeesh.module9.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorContainingIgnoreCase(String author);

/*
    @Query("select t from Book t where lower(t.author) = lower(:author)")
    List<Book> findAllByAuthor(@Param("author") String author);
*/

    @Query("select t from Book t where lower(t.author) = lower(?1)")
    List<Book> findAllByAuthor(String author);

    List<Book> findByAuthor(String author);

    @Modifying
    @Transactional
//    @Query("delete from Book t where t.id = #{book.id}")
    @Query("delete from Book t where t.id = ?1")
    void delete(Long id);
//    void delete(Book book);


    @Query(value = "insert into books(title, author) values(?1,?2)",
            nativeQuery = true)
    void insertBook(String title, String author);

    @Query(value = "select new dev.jlkeesh.module9.dto.BookClassProjection(id, title) from Book")
    List<BookClassProjection> findAllClassProjection();


    @Query("select t.id as id,t.title as title from Book t")
    List<BookInterfaceProjection> findAllInterfaceProjection();

}
