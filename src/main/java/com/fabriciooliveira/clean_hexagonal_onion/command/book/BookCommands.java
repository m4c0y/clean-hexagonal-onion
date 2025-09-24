package com.fabriciooliveira.clean_hexagonal_onion.command.book;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher.PublisherFlow;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books/{id}/commands")
public class BookCommands {

    private final PublisherFlow publisherFlow;

    public BookCommands(PublisherFlow publisherFlow) {
        this.publisherFlow = publisherFlow;
    }

    @PostMapping("/publish")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void publishBook(@PathVariable("id") Long bookId, @RequestBody PublishBookPayload publishBookPayload) {
        publisherFlow.publishBook(bookId, publishBookPayload.publisherId());
    }
}
