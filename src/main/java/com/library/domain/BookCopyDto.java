package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookCopyDto {

    private long id;
   // private long titleId;
    private String bookCopyStatus;

}