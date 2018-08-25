package com.library.controller;

import com.library.mapper.ClerkMapper;
import com.library.domain.BookCopyDto;
import com.library.domain.BookTitlesDto;
import com.library.domain.ClerkDto;
import com.library.domain.ReaderDto;
import com.library.mapper.BookCopyMapper;
import com.library.mapper.BookTitlesMapper;
import com.library.mapper.ReaderMapper;
import com.library.service.DbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private DbService service;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BookTitlesMapper bookTitlesMapper;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Autowired
    private ClerkMapper clerkMapper;

    // 1.dodanie czytelnika,
    @RequestMapping(method = RequestMethod.POST, value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        service.saveReader(readerMapper.mapToReader(readerDto));
    }

    // 2.dodanie tytułu,
    @RequestMapping(method = RequestMethod.POST, value = "createBookTitle", consumes = APPLICATION_JSON_VALUE)
    public void createBookTitle(@RequestBody BookTitlesDto bookTitlesDto) {
        service.saveBookTitle(bookTitlesMapper.mapToBookTitles(bookTitlesDto));
    }

    // 3.dodanie egzemplarza,
    @RequestMapping(method = RequestMethod.POST, value = "createBookCopy", consumes = APPLICATION_JSON_VALUE)
    public void createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        service.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    //4.zmiana statusu egzemplarza,
    @RequestMapping(method = RequestMethod.PUT, value = "updateCopyStatus")
    public BookCopyDto updateStatus(@RequestBody BookCopyDto bookCopyDto) {
        return bookCopyMapper.mapToBookCopyDto(service.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto)));
    }

//    //5.sprawdzenie ilości egzemplarzy danego tytułu dostępnych do wypożyczenia,
//    @RequestMapping(method = RequestMethod.GET, value = "titleAvailability")
//    public Long countCopies(@RequestParam("title") String title) {
//        return service.countCopies(title);
 //   }

    //6.wypożyczenie książki,
    @RequestMapping(method = RequestMethod.POST, value = "checkOut")
    public void bookTakeOut(@RequestBody ClerkDto clerkDto) {
        service.clerkSave(clerkMapper.mapToClerk(clerkDto));
    }

    //7.zwrot książki
    @RequestMapping(method = RequestMethod.PUT, value = "updateCheckIn")
    public ClerkDto updateCheckIn(@RequestBody ClerkDto clerkDto) {
        return clerkMapper.mapToClerkDto(service.clerkSave(clerkMapper.mapToClerk(clerkDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(service.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTitles")
    public List<BookTitlesDto> getTitles() {
        return bookTitlesMapper.mapToBookTitlesDtoList(service.getAlBookTitles());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<BookCopyDto> getBookCopies() {
        return bookCopyMapper.mapToBookCopyDtoList(service.getAlBookCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getClerk")
    public List<ClerkDto> getClerk() {
        return clerkMapper.mapToClerkDtoList(service.getAllClerks());
    }

}
