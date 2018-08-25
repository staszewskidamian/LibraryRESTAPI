package com.library.repository;

import com.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();



   // Optional<BookCopy> findById(Long id);

    @Override
    BookCopy save(BookCopy bookCopy);

}
