package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.SQLData;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReaderDto {

    private long id;
    private String readerName;
    private String readerSecondName;
    private LocalDate timeStarted;

}