package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher;

import com.fabriciooliveira.clean_hexagonal_onion.domain.author.Author;
import com.fabriciooliveira.clean_hexagonal_onion.domain.book.Book;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDataService;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDomainMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherFlowTest {

    @Mock
    private PublisherAppService publisherAppService;

    @Mock
    private BookDataService bookDataService;

    @Mock
    private BookDomainMapper bookDomainMapper;

    @InjectMocks
    private PublisherFlow publisherFlow;

    @Test
    void publishBook_success() {
        // Given
        UUID publisherId = UUID.randomUUID();
        var authorDTO = new AuthorDTO(1L, "firstName", "lastName");
        var bookDTO = new BookDTO(1L, "title", authorDTO, "genre", false, null, null, new ArrayList<>());
        var publisherDTO = new PublisherDTO(publisherId,
                "publisherName");
        var book = new Book(1L, "title", new Author(1L, "firstName", "lastName"), "genre",
                false, null, null, new ArrayList<>());
        when(publisherAppService.getPublisherById(publisherId.toString())).thenReturn(publisherDTO);
        when(bookDataService.findById(1L)).thenReturn(bookDTO);
        when(bookDomainMapper.mapToDomain(bookDTO)).thenReturn(book);

        // When
        publisherFlow.publishBook(1L, publisherId.toString());

        // Then
        ArgumentCaptor<BookDTO> argumentCaptor = ArgumentCaptor.forClass(BookDTO.class);
        verify(bookDataService, times(1)).save(argumentCaptor.capture());
        var capturedArg = argumentCaptor.getValue();
        assertThat(capturedArg.getPublisherId()).isEqualTo(publisherId);
        assertThat(capturedArg.isPublished()).isFalse();
    }

    @Test
    void publishBook_failure_publisherNotFound() {
        // Given
        UUID publisherId = UUID.randomUUID();
        when(publisherAppService.getPublisherById(publisherId.toString())).thenThrow(
                new PublisherAppService.PublisherNotFoundException("Publisher not found!"));

        // When & Then
        assertThrows(PublisherAppService.PublisherNotFoundException.class, () -> publisherFlow.publishBook(1L,
                publisherId.toString()));
    }

    @Test
    void publishBook_failure_bookNotFound() {
        // Given
        UUID publisherId = UUID.randomUUID();
        var publisherDTO = new PublisherDTO(publisherId,
                "publisherName");
        when(publisherAppService.getPublisherById(publisherId.toString())).thenReturn(publisherDTO);
        when(bookDataService.findById(1L)).thenThrow(new BookDataService.BookNotFoundException("Book not found!"));

        // When & Then
        assertThrows(BookDataService.BookNotFoundException.class, () -> publisherFlow.publishBook(1L,
                publisherId.toString()));
    }

    @Test
    void publishBook_failure_bookAlreadyInPublishing() {
        // Given
        UUID publisherId = UUID.randomUUID();
        var authorDTO = new AuthorDTO(1L, "firstName", "lastName");
        var bookDTO = new BookDTO(1L, "title", authorDTO, "genre", false, UUID.randomUUID(), null, new ArrayList<>());
        var publisherDTO = new PublisherDTO(publisherId,
                "publisherName");
        var book = new Book(1L, "title", new Author(1L, "firstName", "lastName"), "genre",
                false, bookDTO.getPublisherId(), null, new ArrayList<>());
        when(publisherAppService.getPublisherById(publisherId.toString())).thenReturn(publisherDTO);
        when(bookDataService.findById(1L)).thenReturn(bookDTO);
        when(bookDomainMapper.mapToDomain(bookDTO)).thenReturn(book);

        // When
        assertThrows(PublisherFlow.BookAlreadyInPublishingException.class, () -> publisherFlow.publishBook(1L,
                publisherId.toString()));
    }
}
