package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorJPA;
import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorRepository;
import com.fabriciooliveira.clean_hexagonal_onion.domain.book.Book;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDataService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringJUnitConfig
@SpringBootTest
class BookJPATest {

    @MockitoBean
    private TestEventHandler eventHandler;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDataService bookDataService;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void beforeAll() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    void shouldPublishEventOnSavingAggregate() {
        // Given
        var authorJPA = AuthorJPA.builder().firstName("first").lastName("last").build();
        authorRepository.save(authorJPA);
        authorJPA = authorRepository.findAll().get(0);

        UUID publisherId = UUID.randomUUID();
        AuthorDTO authorDTO = new AuthorDTO(authorJPA.getId(), authorJPA.getFirstName(),
                authorJPA.getLastName());
        var bookDTO = new BookDTO(null,
                "Title",
                authorDTO,
                "Horror",
                false,
                publisherId,
                null, List.of(new Book.RequestPublishingEvent(1L, publisherId)));

        // When
        bookDataService.save(bookDTO);

        // Then
        ArgumentCaptor<Book.RequestPublishingEvent> argumentCaptor = ArgumentCaptor.forClass(Book.RequestPublishingEvent.class);
        verify(eventHandler, times(1)).handleEvent(argumentCaptor.capture());
    }

    interface TestEventHandler {
        @TransactionalEventListener
        void handleEvent(Book.RequestPublishingEvent event);
    }
}
