package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.BookCopyDto;
import com.library.repository.BookTitlesRepository;
import com.library.repository.ClerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    @Autowired
    private BookTitlesRepository bookTitlesRepository;
    @Autowired
    private ClerkRepository clerkRepository;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getBookCopyStatus()
               // bookTitlesRepository.findByBookCopies(bookCopyDto.getTitleId()).get()

        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
             //   bookCopy.getBookTitles().getId(),
                bookCopy.getBookCopyStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(t -> new BookCopyDto(t.getId() , t.getBookCopyStatus()))
                .collect(Collectors.toList());
    }
}
