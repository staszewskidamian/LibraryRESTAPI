package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "bookCopy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "status")
    private String bookCopyStatus;

//    @ManyToOne
//    @JoinColumn(name = "titleId")
//    private BookTitles bookTitles;

//    @OneToMany(targetEntity = Clerk.class,
//            mappedBy = "bookCopy",
//            cascade = CascadeType.ALL)
//        private List<Clerk> clerksId = new ArrayList<>();

}
