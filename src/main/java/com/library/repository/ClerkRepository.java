package com.library.repository;

import com.library.domain.Clerk;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClerkRepository extends CrudRepository<Clerk, Long> {

    @Override
    List<Clerk> findAll();

    @Override
    Clerk save(Clerk clerk);

    Optional<Clerk> findById(Long id);
}
