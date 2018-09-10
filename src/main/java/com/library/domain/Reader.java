package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String readerName;

    @Column(name = "second_name")
    private String readerSecondName;

    @Column(name = "time_started")
    private LocalDate timeStarted;

    @OneToMany(targetEntity = Clerk.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL)
    private List<Clerk> readerId = new ArrayList<>();
}
