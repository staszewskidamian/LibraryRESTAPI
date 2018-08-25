package com.library.mapper;

import com.library.domain.BookTitles;
import com.library.domain.BookTitlesDto;
import com.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTitlesMapper {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookTitles mapToBookTitles(final BookTitlesDto bookTitlesDto) {
        return new BookTitles(
                bookTitlesDto.getTitleId(),
                bookTitlesDto.getTitle(),
                bookTitlesDto.getAuthor(),
                bookTitlesDto.getPublicationYear()
              //  bookCopyRepository.findAll()
        );
    }

    public BookTitlesDto mapToBookTitlesDto(final BookTitles bookTitles) {
        return new BookTitlesDto(
                bookTitles.getId(),
                bookTitles.getTitle(),
                bookTitles.getAuthor(),
                bookTitles.getPublicationYear()
        );
    }

    public List<BookTitlesDto> mapToBookTitlesDtoList(final List<BookTitles> bookTitles) {
        return bookTitles.stream()
                .map(t -> new BookTitlesDto(t.getId(), t.getAuthor(),
                        t.getTitle(), t.getPublicationYear()))
                .collect(Collectors.toList());
    }

}
