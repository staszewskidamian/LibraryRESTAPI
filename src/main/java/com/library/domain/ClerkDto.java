package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClerkDto {

    private long id;
    private LocalDate checkOut;
    private LocalDate checkIn;
    private long readerId;
    private long bookCopyId;
}