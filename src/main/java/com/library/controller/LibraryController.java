package com.library.controller;

import com.library.domain.*;
import com.library.mapper.ClerkMapper;
import com.library.mapper.BookCopyMapper;
import com.library.mapper.BookTitlesMapper;
import com.library.mapper.ReaderMapper;
import com.library.service.DbService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class LibraryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);

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
        System.out.println(readerDto);
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
        if (bookCopyDto.getTitleId() <= service.getAlBookTitles().size()) {
            service.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
        } else {
            LOGGER.info("Input bigger than available");
        }
    }

    //4.zmiana statusu egzemplarza,
    @RequestMapping(method = RequestMethod.PUT, value = "updateCopyStatus")
    public void updateStatus(Long bookCopyId, String newStatus) {
        BookCopy bookCopy = service.getBookCopyById(bookCopyId);
        BookCopyDto bookCopyDto = new BookCopyDto(bookCopyId, newStatus, bookCopy.getBookTitles().getId(), bookCopy.getBookCopyId());
        bookCopyMapper.mapToBookCopyDto(service.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto)));
    }

    //5.sprawdzenie ilości egzemplarzy danego tytułu dostępnych do wypożyczenia,
    @RequestMapping(method = RequestMethod.GET, value = "titleAvailability")
    public Long countCopies(@RequestParam("title") String title) {
        return service.countCopies(title);
    }

    //6.wypożyczenie książki,
    @RequestMapping(method = RequestMethod.POST, value = "checkOut")
    public void bookTakeOut(@RequestBody ClerkDto clerkDto) {
        if (clerkDto.getBookCopyId() <= service.getAlBookCopies().size() && clerkDto.getReaderId() <= service.getAllReaders().size()) {
            service.clerkSave(clerkMapper.mapToClerk(clerkDto));
        } else {
            LOGGER.info("Input bigger than available");
        }
    }

    //7.zwrot książki
    @RequestMapping(method = RequestMethod.PUT, value = "updateCheckIn")
    public void updateCheckIn(Long clerkId) {
        Clerk clerk = service.getClerkById(clerkId);
        ClerkDto clerkDto = new ClerkDto(clerkId, clerk.getCheckOut(), LocalDate.now(), clerk.getReader().getId(), clerk.getBookCopy().getCopyId());
        clerkMapper.mapToClerkDto(service.clerkSave(clerkMapper.mapToClerk(clerkDto)));
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
