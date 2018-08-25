package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClerkDto {

    private long id;
    //private long bookCopyId;
    private long readerId;
    private String checkOut;
    private String checkIn;

}