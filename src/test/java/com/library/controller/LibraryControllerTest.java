package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookCopyMapper;
import com.library.mapper.BookTitlesMapper;
import com.library.mapper.ClerkMapper;
import com.library.mapper.ReaderMapper;
import com.library.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        Clerk clerk = new Clerk();
        List<Clerk> clerks = new ArrayList<>();
        clerks.add(clerk);
        Reader reader = new Reader(1, "name", "secondName", null, new ArrayList<>());
        when(service.saveReader(readerMapper.mapToReader(any(ReaderDto.class)))).thenReturn(reader);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(reader);
        //When & Then
        mockMvc.perform(post("/library/createReader")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).saveReader(any());
    }

    @Test
    public void shouldCreateBookTitle() throws Exception {
        //Given
        BookCopy bookCopy = new BookCopy();
        List<BookCopy> copies = new ArrayList<>();
        copies.add(bookCopy);
        BookTitles bookTitles = new BookTitles(1, "title", "author", "2009", copies);
        when(service.saveBookTitle(bookTitlesMapper.mapToBookTitles(any(BookTitlesDto.class)))).thenReturn(bookTitles);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookTitles);
        //When & Then
        mockMvc.perform(post("/library/createBookTitle")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).saveBookTitle(any());
    }

    @Test
    public void shouldCreateBookCopy() throws Exception {
        //Given
        BookTitles bookTitles = new BookTitles();
        Clerk clerk = new Clerk();
        List<Clerk> clerks = new ArrayList<>();
        clerks.add(clerk);
        BookCopy bookCopy = new BookCopy(1, "busy", bookTitles, clerks);
        when(service.saveBookCopy((any(BookCopy.class)))).thenReturn(bookCopy);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopy);
        //When & Then
        mockMvc.perform(post("/library/createBookCopy")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).saveBookCopy(any());
    }

    @Test
    public void shouldCountCopies() throws Exception {

        when(service.countCopies(anyString())).thenReturn(10L);
        mockMvc.perform(get("/library/titleAvailability")
                .param("title", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(10));
    }

    @Test
    public void shouldBookTakeOut() throws Exception {
        //Given
        Reader reader = new Reader();
        BookCopy bookCopy = new BookCopy();
        Clerk clerk = new Clerk(1L, null, null, reader, bookCopy);
        when(service.clerkSave(clerkMapper.mapToClerk(any(ClerkDto.class)))).thenReturn(clerk);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(clerk);
        //When & Then
        mockMvc.perform(post("/library/checkOut")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(1)).clerkSave(clerkMapper.mapToClerk(any()));
    }

    @Test
    public void shouldUpdateStatus() throws Exception {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(1, "lost", 1, new ArrayList<>());
        BookTitles bookTitles = new BookTitles(1, null, null, null, null);
        when(service.getBookCopyById(1)).thenReturn(new BookCopy(1, null, bookTitles, null));
        when(bookCopyMapper.mapToBookCopyDto(service.saveBookCopy(bookCopyMapper.mapToBookCopy(any(BookCopyDto.class))))).thenReturn(bookCopyDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopyDto);
        //When & Then
        mockMvc.perform(put("/library/updateCopyStatus?bookCopyId=1&&newStatus=test")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(2)).saveBookCopy(any());
    }

    @Test
    public void shouldUpdateCheckIn() throws Exception {
        //Given
        Reader reader = new Reader(1, null, null, null, null);
        BookCopy bookCopy = new BookCopy(1, null, null, null);
        Clerk clerk = new Clerk(1, LocalDate.now(), LocalDate.now(), reader, bookCopy);
        ClerkDto clerkDto = new ClerkDto(1, LocalDate.now(), LocalDate.now(), 1, 1);
        when(service.getClerkById(1)).thenReturn(clerk);
        when(clerkMapper.mapToClerkDto(service.clerkSave(clerkMapper.mapToClerk(any(ClerkDto.class))))).thenReturn(clerkDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(clerk);
        //When & Then
        mockMvc.perform(put("/library/updateCheckIn")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("clerkId", "1")
                .content(jsonContent))
                .andExpect(status().is(200));
        verify(service, times(2)).clerkSave(any());
    }

    @Test
    public void shouldGetTitles() throws Exception {
        //Given
        List<BookTitlesDto> bookTitlesDtoList = new ArrayList<>();
        bookTitlesDtoList.add(new BookTitlesDto());
        bookTitlesDtoList.add(new BookTitlesDto());
        when(bookTitlesMapper.mapToBookTitlesDtoList(service.getAlBookTitles())).thenReturn(bookTitlesDtoList);
        //When & Then
        mockMvc.perform(get("/library/getTitles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", isEmptyOrNullString()))
                .andExpect(jsonPath("$[0].author", isEmptyOrNullString()))
                .andExpect(jsonPath("$[1].id", is(0)));
    }

}