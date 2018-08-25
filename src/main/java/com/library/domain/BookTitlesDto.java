package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookTitlesDto {

    private long titleId;
    private String title;
    private String author;
    private String publicationYear;

}
