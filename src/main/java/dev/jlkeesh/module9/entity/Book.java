package dev.jlkeesh.module9.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@JsonIgnoreProperties(value = {"author"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("book-ning-idsi")
    private Long id;

    @JsonProperty("book-ning-titleli")
    private String title;

    private String author;

    public String getAuthorwe() {
        return "sss" + author;
    }

/*    @JsonIgnore
    @JsonProperty("transacId")
    private String transactionId;*/

}
