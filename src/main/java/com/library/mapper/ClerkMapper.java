package com.library.mapper;

import com.library.domain.*;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClerkMapper {

    @Autowired
    private DbService dbService;

    public Clerk mapToClerk(final ClerkDto clerkDto) {
        return new Clerk(
                clerkDto.getId(),
                clerkDto.getCheckOut(),
                clerkDto.getCheckIn(),
                dbService.getReaderById(clerkDto.getReaderId()),
                dbService.getBookCopyById(clerkDto.getBookCopyId())
        );
    }

    public ClerkDto mapToClerkDto(final Clerk clerk) {
        return new ClerkDto(
                clerk.getId(),
                clerk.getCheckOut(),
                clerk.getCheckIn(),
                clerk.getReader().getId(),
                clerk.getBookCopy().getCopyId()
        );
    }

    public List<ClerkDto> mapToClerkDtoList(final List<Clerk> clerks) {
        return clerks.stream()
                .map(t -> new ClerkDto(t.getId(), t.getCheckOut(),
                        t.getCheckIn(), t.getReader().getId(), t.getBookCopy().getCopyId()))
                .collect(Collectors.toList());
    }
}
