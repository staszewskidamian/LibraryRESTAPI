package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookCopyMapper;
import com.library.mapper.BookTitlesMapper;
import com.library.mapper.ClerkMapper;
import com.library.mapper.ReaderMapper;
import com.library.repository.ClerkRepository;
import com.library.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import com.google.gson.Gson;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private BookCopyMapper bookCopyMapper;

    @MockBean
    private BookTitlesMapper bookTitlesMapper;

    @MockBean
    private ReaderMapper readerMapper;

    @MockBean
    private ClerkMapper clerkMapper;

    @Test
    public void shouldCreateReader() throws Exception {
        //Given

        Reader reader = new Reader();
        ReaderDto readerDto = new ReaderDto(1L,"name","secondName",LocalDate.of(2012,10,10));
        when(service.saveReader(readerMapper.mapToReader(any(ReaderDto.class)))).thenReturn(reader);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(reader);

        //When & Then
        mockMvc.perform(post("/library/createReader")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).saveReader(readerMapper.mapToReader(readerDto));

    }

    @Test
    public void shouldCreateBookTitle() throws Exception {

        //Given
        BookTitles bookTitles = new BookTitles();
        BookTitlesDto bookTitlesDto = new BookTitlesDto();
        when(service.saveBookTitle(bookTitlesMapper.mapToBookTitles(any(BookTitlesDto.class)))).thenReturn(bookTitles);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookTitles);
        //When & Then
        mockMvc.perform(post("/library/createBookTitle")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).saveBookTitle(bookTitlesMapper.mapToBookTitles(bookTitlesDto));

    }
    @Test
    public void shouldCreateBookCopy() throws Exception {
        //Given
        BookCopy bookCopy = new BookCopy();
        BookCopyDto bookCopyDto = new BookCopyDto();
        when(service.saveBookCopy(bookCopyMapper.mapToBookCopy(any(BookCopyDto.class)))).thenReturn(bookCopy);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopy);
        //When & Then
        mockMvc.perform(post("/library/createBookCopy")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));


    }

    @Test
    public void shouldUpdateStatus() throws Exception {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(1,1,"sfg");
        when(bookCopyMapper.mapToBookCopyDto(service.saveBookCopy(bookCopyMapper.mapToBookCopy(any(BookCopyDto.class))))).thenReturn(bookCopyDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopyDto);
        //When & Then
        //When & Then
        mockMvc.perform(put("/library/updateCopyStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.copyId", is(1)))
                .andExpect(jsonPath("$.bookCopyStatus", is("sfg")))
                .andExpect(jsonPath("$.titleId", is(1)));


    }

    @Test
    public void shouldCountCopies() throws Exception {


    }
}