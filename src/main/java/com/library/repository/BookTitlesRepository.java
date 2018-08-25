package com.library.repository;

import com.library.domain.BookTitles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface BookTitlesRepository extends CrudRepository<BookTitles, Long> {

    @Override
    List<BookTitles> findAll();

    @Override
    BookTitles save(BookTitles bookTitles);

   // Optional<BookTitles> findByBookCopies(Long id);

}
