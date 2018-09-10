package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "book_titles")
public class BookTitles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publicationYear")
    private String publicationYear;

    @OneToMany(targetEntity = BookCopy.class,
            mappedBy = "bookTitles",
            cascade = CascadeType.ALL)
    private List<BookCopy> bookCopies = new ArrayList<>();
}
