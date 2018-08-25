package com.library.repository;

import com.library.domain.Clerk;

import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface ClerkRepository extends CrudRepository<Clerk, Long> {

    @Override
    List<Clerk> findAll();

    @Override
    Clerk save(Clerk clerk);

}
