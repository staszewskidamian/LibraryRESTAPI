package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.BookCopyDto;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    @Autowired
    private DbService dbService;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getBookCopyStatus(),
                dbService.getBookTitleById(bookCopyDto.getTitleId()),
                bookCopyDto.getBookCopyId()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getCopyId(),
                bookCopy.getBookCopyStatus(),
                bookCopy.getBookTitles().getId(),
                bookCopy.getBookCopyId()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(t -> new BookCopyDto(t.getCopyId(), t.getBookCopyStatus(), t.getBookTitles().getId(),
                        t.getBookCopyId()))
                .collect(Collectors.toList());
    }
}
