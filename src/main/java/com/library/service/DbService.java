package com.library.service;

import com.library.domain.*;
import com.library.repository.BookCopyRepository;
import com.library.repository.BookTitlesRepository;
import com.library.repository.ClerkRepository;
import com.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookTitlesRepository bookTitlesRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private ClerkRepository clerkRepository;

    public BookTitles getBookTitleById(final long id) {
        return bookTitlesRepository.findById(id).orElse(null);
    }

    public Reader getReaderById(final long id) {
        return readerRepository.findById(id).orElse(null);
    }

    public BookCopy getBookCopyById(final long id) {
        return bookCopyRepository.findByCopyId(id).orElse(null);
    }

    public Clerk getClerkById(final long id) {
        return clerkRepository.findById(id).orElse(null);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public BookTitles saveBookTitle(final BookTitles bookTitles) {
        return bookTitlesRepository.save(bookTitles);
    }

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public Long countCopies(final String title) {
        return bookCopyRepository.findAll().stream()
                .filter(i -> i.getBookCopyStatus().equals("available"))
                .filter(i -> i.getBookTitles().getTitle().equals(title))
                .count();
    }

    public Clerk clerkSave(final Clerk clerk) {
        return clerkRepository.save(clerk);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public List<Clerk> getAllClerks() {
        return clerkRepository.findAll();
    }

    public List<BookTitles> getAlBookTitles() {
        return bookTitlesRepository.findAll();
    }

    public List<BookCopy> getAlBookCopies() {
        return bookCopyRepository.findAll();
    }
}
