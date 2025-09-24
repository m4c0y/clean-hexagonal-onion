package com.fabriciooliveira.clean_hexagonal_onion.domain.book;

import com.fabriciooliveira.clean_hexagonal_onion.domain.DomainEvent;
import com.fabriciooliveira.clean_hexagonal_onion.domain.author.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class Book {

    private Long id;
    private String title;
    private Author author;
    private String genre;
    private boolean published;
    private UUID publisherId;
    private String isbn;
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public static Book createBook(String title, String genre, Author author) {
        return new Book(null, title, author, genre, false, null, null, new ArrayList<>());
    }

    public void requestPublishing(UUID publisherId) {
        this.publisherId = publisherId;
        domainEvents.add(new RequestPublishingEvent(this.id, this.publisherId));
    }

    public boolean canBePublished() {
        return publisherId == null && !published;
    }

    public void updatePublishingInfo(String isbn) {
        this.isbn = isbn;
        this.published = true;
    }

    @Value
    public static class RequestPublishingEvent extends DomainEvent {
        Long bookId;
        UUID publisherId;
    }
}
