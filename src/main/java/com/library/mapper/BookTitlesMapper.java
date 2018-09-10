package com.library.mapper;

import com.library.domain.BookTitles;
import com.library.domain.BookTitlesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTitlesMapper {

    public BookTitles mapToBookTitles(final BookTitlesDto bookTitlesDto) {
        return new BookTitles(
                bookTitlesDto.getId(),
                bookTitlesDto.getTitle(),
                bookTitlesDto.getAuthor(),
                bookTitlesDto.getPublicationYear(),
                bookTitlesDto.getBookCopies()
        );
    }

    public BookTitlesDto mapToBookTitlesDto(final BookTitles bookTitles) {
        return new BookTitlesDto(
                bookTitles.getId(),
                bookTitles.getTitle(),
                bookTitles.getAuthor(),
                bookTitles.getPublicationYear(),
                bookTitles.getBookCopies()
        );
    }

    public List<BookTitlesDto> mapToBookTitlesDtoList(final List<BookTitles> bookTitles) {
        return bookTitles.stream()
                .map(t -> new BookTitlesDto(t.getId(), t.getTitle(), t.getAuthor(),
                        t.getPublicationYear(), t.getBookCopies()))
                .collect(Collectors.toList());
    }
}
