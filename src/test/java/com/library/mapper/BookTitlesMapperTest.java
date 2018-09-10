package com.library.mapper;

import com.library.domain.BookTitles;
import com.library.domain.BookTitlesDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTitlesMapperTest {

    @Autowired
    private BookTitlesMapper mapper;

    @Test
    public void shouldMapToBookTitles() {
        //Given
        BookTitlesDto bookTitlesDto = new BookTitlesDto(1, "title", "author", "year", new ArrayList<>());
        //When
        BookTitles bookTitles = mapper.mapToBookTitles(bookTitlesDto);
        //Then
        assertEquals(1, bookTitles.getId());
        assertEquals("title", bookTitles.getTitle());
        assertEquals("author", bookTitles.getAuthor());
        assertEquals("year", bookTitles.getPublicationYear());
        assertTrue(bookTitles.getBookCopies().isEmpty());
    }

    @Test
    public void shouldMapToBookTitlesDto() {
        //Given
        BookTitles bookTitles = new BookTitles(1, "title", "author", "year", new ArrayList<>());
        //When
        BookTitlesDto bookTitlesDto = mapper.mapToBookTitlesDto(bookTitles);
        //Then
        assertEquals(1, bookTitlesDto.getId());
        assertEquals("title", bookTitlesDto.getTitle());
        assertEquals("author", bookTitlesDto.getAuthor());
        assertEquals("year", bookTitlesDto.getPublicationYear());
        assertTrue(bookTitlesDto.getBookCopies().isEmpty());
    }

    @Test
    public void shouldMapToBookTitlesDtoList() {
        //Given
        BookTitles bookTitles = new BookTitles(1, "title", "author", "year", new ArrayList<>());
        List<BookTitles> bookTitlesList = new ArrayList<>();
        bookTitlesList.add(bookTitles);
        //When
        List<BookTitlesDto> bookTitlesDtoList = mapper.mapToBookTitlesDtoList(bookTitlesList);
        assertEquals(1, bookTitlesDtoList.get(0).getId());
        assertEquals("title", bookTitlesDtoList.get(0).getTitle());
        assertEquals("author", bookTitlesDtoList.get(0).getAuthor());
        assertEquals("year", bookTitlesDtoList.get(0).getPublicationYear());
        assertTrue(bookTitlesDtoList.get(0).getBookCopies().isEmpty());
    }
}