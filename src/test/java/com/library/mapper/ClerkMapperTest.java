package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.Clerk;
import com.library.domain.ClerkDto;
import com.library.domain.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClerkMapperTest {

    @Autowired
    private ClerkMapper mapper;

    @Test
    public void shouldMapToClerk() {
        //Given
        ClerkDto clerkDto = new ClerkDto(1, LocalDate.of(1998, 10, 10), null, 1, 1);
        //When
        Clerk clerk = mapper.mapToClerk(clerkDto);
        //Then
        assertEquals(1, clerk.getId());
        assertEquals(1, clerk.getReader().getId());
        assertEquals(1, clerk.getBookCopy().getCopyId());
        assertEquals(LocalDate.of(1998, 10, 10), clerk.getCheckOut());
        assertEquals(null, clerk.getCheckIn());
    }

    @Test
    public void shouldMapToClerkDto() {
        //Given
        Reader reader = new Reader(1, "", "", LocalDate.of(1998, 10, 10), null);
        BookCopy bookCopy = new BookCopy(1, "available", null, null);
        Clerk clerk = new Clerk(1, LocalDate.of(1998, 10, 10), null, reader, bookCopy);
        //When
        ClerkDto clerkDto = mapper.mapToClerkDto(clerk);
        //Then
        assertEquals(1, clerk.getId());
        assertEquals(1, clerk.getReader().getId());
        assertEquals(1, clerk.getBookCopy().getCopyId());
        assertEquals(LocalDate.of(1998, 10, 10), clerk.getCheckOut());
        assertEquals(null, clerk.getCheckIn());
    }

    @Test
    public void shouldMapToClerkDtoList() {
        //Given
        Reader reader = new Reader(1, "", "", LocalDate.of(1998, 10, 10), null);
        BookCopy bookCopy = new BookCopy(1, "available", null, null);
        Clerk clerk = new Clerk(1, LocalDate.of(1998, 10, 10), null, reader, bookCopy);
        List<Clerk> clerkList = new ArrayList<>();
        clerkList.add(clerk);
        //When
        List<ClerkDto> clerkDtos = mapper.mapToClerkDtoList(clerkList);
        //Then
        assertEquals(1, clerkDtos.get(0).getId());
        assertEquals(1, clerkDtos.get(0).getReaderId());
        assertEquals(1, clerkDtos.get(0).getBookCopyId());
        assertEquals(LocalDate.of(1998, 10, 10), clerkDtos.get(0).getCheckOut());
        assertEquals(null, clerkDtos.get(0).getCheckIn());
    }
}