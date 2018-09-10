package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookTitlesDto {

    private long id;
    private String title;
    private String author;
    private String publicationYear;
    private List<BookCopy> bookCopies;
}
