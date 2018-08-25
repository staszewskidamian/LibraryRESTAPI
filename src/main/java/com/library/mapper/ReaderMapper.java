package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import com.library.repository.ClerkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    @Autowired
    private ClerkRepository clerkRepository;

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getReaderName(),
                readerDto.getReaderSecondName(),
                readerDto.getTimeStarted(),
                clerkRepository.findAll()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getReaderName(),
                reader.getReaderSecondName(),
                reader.getTimeStarted()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(t -> new ReaderDto(t.getId(), t.getReaderName(), t.getReaderSecondName(),
                        t.getTimeStarted()))
                .collect(Collectors.toList());
    }
}
