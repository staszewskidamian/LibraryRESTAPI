package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookCopyDto {

    private long id;
    private String bookCopyStatus;
    private long titleId;
    private List<Clerk> bookCopyId;
}