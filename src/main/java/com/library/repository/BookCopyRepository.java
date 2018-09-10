package com.library.repository;

import com.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    Optional<BookCopy> findByCopyId(Long id);

    @Override
    BookCopy save(BookCopy bookCopy);
}
