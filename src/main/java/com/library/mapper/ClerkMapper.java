package com.library.mapper;

import com.library.domain.*;
import com.library.repository.BookCopyRepository;
import com.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClerkMapper {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public Clerk mapToClerk(final ClerkDto clerkDto) {
        return new Clerk(
                clerkDto.getId(),
                clerkDto.getCheckOut(),
                clerkDto.getCheckIn(),
                readerRepository.findByReaderId(clerkDto.getId()).get()
              //  bookCopyRepository.f(clerkDto.getBookCopyId()).get()
        );
    }

    public ClerkDto mapToClerkDto(final Clerk clerk) {
        return new ClerkDto(
                clerk.getId(),
               // clerk.getBookCopy().getId(),
                clerk.getReader().getId(),
                clerk.getCheckIn(),
                clerk.getCheckOut()
        );
    }

    public List<ClerkDto> mapToClerkDtoList(final List<Clerk> clerks) {
        return clerks.stream()
                .map(t -> new ClerkDto(t.getId(), t.getReader().getId(),
                        t.getCheckOut(), t.getCheckIn()))
                .collect(Collectors.toList());
    }
}
