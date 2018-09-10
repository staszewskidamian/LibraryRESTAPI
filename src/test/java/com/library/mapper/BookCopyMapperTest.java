package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.BookCopyDto;
import com.library.domain.BookTitles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyMapperTest {

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Test
    public void shouldMapToBookCopy() {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(1, "busy", 1, new ArrayList<>());
        //When
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        //Then
        assertEquals("busy", bookCopy.getBookCopyStatus());
        assertEquals(1, bookCopy.getCopyId());
        assertEquals(1, bookCopy.getBookTitles().getId());
        assertEquals(Collections.emptyList(), bookCopy.getBookCopyId());
        assertTrue(bookCopy.getBookCopyId().isEmpty());
    }

    @Test
    public void shouldMapToBookCopyDto() {
        //Given
        BookTitles bookTitles = new BookTitles(1, "title", "author", "1998", null);
        BookCopy bookCopy = new BookCopy(1, "busy", bookTitles, null);
        //When
        BookCopyDto bookCopyDto = bookCopyMapper.mapToBookCopyDto(bookCopy);
        //Then
        assertEquals("busy", bookCopyDto.getBookCopyStatus());
        assertEquals(1, bookCopyDto.getId());
        assertEquals(null, bookCopyDto.getBookCopyId());
        assertEquals(1, bookCopyDto.getTitleId());
    }

    @Test
    public void shouldMapToBookCopyDtoList() {
        //Given
        BookTitles bookTitles = new BookTitles(1, "title", "author", "1998", null);
        BookCopy bookCopy = new BookCopy(1, "busy", bookTitles, new ArrayList<>());
        List<BookCopy> bookCopies = new ArrayList<>();
        bookCopies.add(bookCopy);
        // When
        List<BookCopyDto> bookCopyDtos = bookCopyMapper.mapToBookCopyDtoList(bookCopies);
        //Then
        assertEquals("busy", bookCopyDtos.get(0).getBookCopyStatus());
        assertEquals(1, bookCopyDtos.get(0).getId());
        assertEquals(1, bookCopyDtos.get(0).getTitleId());
        assertTrue(bookCopyDtos.get(0).getBookCopyId().isEmpty());
    }
}
