package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDataService;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDomainMapper;
import org.springframework.stereotype.Service;

@Service
public class PublisherFlow {

    private final PublisherAppService publisherAppService;
    private final BookDataService bookDataService;
    private final BookDomainMapper bookDomainMapper;

    public PublisherFlow(PublisherAppService publisherAppService, BookDataService bookDataService, BookDomainMapper bookDomainMapper) {
        this.publisherAppService = publisherAppService;
        this.bookDataService = bookDataService;
        this.bookDomainMapper = bookDomainMapper;
    }

    public void publishBook(Long bookId, String publisherId) {
        var publisherDTO = publisherAppService.getPublisherById(publisherId);
        var bookDTO = bookDataService.findById(bookId);
        var book = bookDomainMapper.mapToDomain(bookDTO);

        if (!book.canBePublished()) {
            throw new BookAlreadyInPublishingException("Book already in publishing!");
        }

        book.requestPublishing(publisherDTO.getId());
        var updatedBookDto = new BookDTO(book);
        bookDataService.save(updatedBookDto);
    }

    static class BookAlreadyInPublishingException extends RuntimeException {
        public BookAlreadyInPublishingException(String errorMessage) {
            super(errorMessage);
        }
    }
}
