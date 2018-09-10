package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getReaderName(),
                readerDto.getReaderSecondName(),
                readerDto.getTimeStarted(),
                readerDto.getReaderId()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getReaderName(),
                reader.getReaderSecondName(),
                reader.getTimeStarted(),
                reader.getReaderId()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(t -> new ReaderDto(t.getId(), t.getReaderName(), t.getReaderSecondName(),
                        t.getTimeStarted(),t.getReaderId()))
                .collect(Collectors.toList());
    }
}
