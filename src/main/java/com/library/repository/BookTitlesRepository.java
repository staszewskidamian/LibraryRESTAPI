package com.library.repository;

import com.library.domain.BookTitles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookTitlesRepository extends CrudRepository<BookTitles, Long> {

    @Override
    List<BookTitles> findAll();

    @Override
    BookTitles save(BookTitles bookTitles);

    Optional<BookTitles> findById(Long id);
}
