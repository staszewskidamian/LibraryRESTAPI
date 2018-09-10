package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReaderDto {

    private long id;
    private String readerName;
    private String readerSecondName;
    private LocalDate timeStarted;
    private List<Clerk> readerId;
}