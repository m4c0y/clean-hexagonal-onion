package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book;

import com.fabriciooliveira.clean_hexagonal_onion.domain.DomainEvent;
import com.fabriciooliveira.clean_hexagonal_onion.domain.book.Book;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private AuthorDTO authorDTO;
    private String genre;
    private boolean published;
    private UUID publisherId;
    private String isbn;
    private List<DomainEvent> domainEvents;

    public BookDTO(Book book) {
        this(book.getId(), book.getTitle(), new AuthorDTO(book.getAuthor()), book.getGenre(), book.isPublished(), book.getPublisherId(), book.getIsbn(), book.getDomainEvents());
    }
}
