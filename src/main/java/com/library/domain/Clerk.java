package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "clerk")
public class Clerk {

    @Id
    private long id;

    @Column(name = "checkOut")
    private String checkOut;


    @Column(name = "checkIn")
    private String checkIn;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Reader reader;

//    @ManyToOne
//    @JoinColumn(name = "clerksId")
//    private BookCopy bookCopy;
}
