package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTest {

    @Autowired
    private ReaderMapper mapper;

    @Test
    public void shouldMapToReader() {
        //Given
        ReaderDto readerDto = new ReaderDto(1, "firstname", "lastname", LocalDate.of(1998, 10, 10), new ArrayList<>());
        //When
        Reader reader = mapper.mapToReader(readerDto);
        //Then
        assertEquals(1, reader.getId());
        assertEquals("firstname", reader.getReaderName());
        assertEquals("lastname", reader.getReaderSecondName());
        assertEquals(LocalDate.of(1998, 10, 10), reader.getTimeStarted());
        assertTrue(reader.getReaderId().isEmpty());
    }

    @Test
    public void shouldMapToReaderDto() {
        //Given
        Reader reader = new Reader(1, "firstname", "lastname", LocalDate.of(1998, 10, 10), new ArrayList<>());
        //When
        ReaderDto readerDto = mapper.mapToReaderDto(reader);
        //Then
        assertEquals(1, readerDto.getId());
        assertEquals("firstname", readerDto.getReaderName());
        assertEquals("lastname", readerDto.getReaderSecondName());
        assertEquals(LocalDate.of(1998, 10, 10), readerDto.getTimeStarted());
        assertTrue(readerDto.getReaderId().isEmpty());
    }

    @Test
    public void shouldMapToReaderDtoList() {
        //Given
        Reader reader = new Reader(1, "firstname", "lastname", LocalDate.of(1998, 10, 10), new ArrayList<>());
        List<Reader> readers = new ArrayList<>();
        readers.add(reader);
        //When
        List<ReaderDto> readerDtos = mapper.mapToReaderDtoList(readers);
        //Then
        assertEquals(1, readerDtos.get(0).getId());
        assertEquals("firstname", readerDtos.get(0).getReaderName());
        assertEquals("lastname", readerDtos.get(0).getReaderSecondName());
        assertEquals(LocalDate.of(1998, 10, 10), readerDtos.get(0).getTimeStarted());
        assertTrue(readerDtos.get(0).getReaderId().isEmpty());
    }
}