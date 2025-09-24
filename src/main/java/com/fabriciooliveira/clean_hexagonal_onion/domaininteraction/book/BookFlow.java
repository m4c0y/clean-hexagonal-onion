package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher.PublisherAppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookFlow {

    private final BookDataService bookDataService;
    private final PublisherAppService publisherAppService;
    private final BookDomainMapper bookDomainMapper;

    public BookFlow(BookDataService bookDataService, PublisherAppService publisherAppService, BookDomainMapper bookDomainMapper) {
        this.bookDataService = bookDataService;
        this.publisherAppService = publisherAppService;
        this.bookDomainMapper = bookDomainMapper;
    }

    public List<BookDTO> findAll() {
        return bookDataService.findAll();
    }

    public List<BookDTO> findByTitleContaining(String title) {
        return bookDataService.findByTitleContaining(title);
    }

    public void requestPublishingAtPublisher(UUID publisherId, Long bookId) {
        var bookDTO = bookDataService.findById(bookId);
        var isbn = publisherAppService.requestPublishing(
                publisherId,
                bookDTO.getAuthorDTO().getFullName(),
                bookDTO.getTitle());

        var book = bookDomainMapper.mapToDomain(bookDTO);
        book.updatePublishingInfo(isbn);
        var updatedBookDTO = new BookDTO(book);

        bookDataService.save(updatedBookDTO);
    }
}
