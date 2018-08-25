package com.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @JsonFormat(pattern = "dd::MM::yyyy")
    private LocalDate timeStarted;


    @OneToMany(targetEntity = Clerk.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL)
    private List<Clerk> readerId;

}
