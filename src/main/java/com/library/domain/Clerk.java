package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "clerk")
public class Clerk {

    @Id
    private long id;

    @Column(name = "checkOut")
    private LocalDate checkOut;

    @Column(name = "checkIn")
    private LocalDate checkIn;

    @ManyToOne
    @JoinColumn(name = "readers")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "copies")
    private BookCopy bookCopy;
}
