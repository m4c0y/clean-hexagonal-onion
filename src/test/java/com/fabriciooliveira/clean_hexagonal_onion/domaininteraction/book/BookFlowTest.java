package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookFlowTest {

    @Mock
    private BookDataService bookDataService;

    @InjectMocks
    private BookFlow bookFlow;

    @Test
    void findAllBooks() {
        // Given
        when(bookDataService.findAll()).thenReturn(List.of(
                new BookDTO(1L, "title", new AuthorDTO(1L, "Tom", "Someone"), "genre", false, null, null,
                        new ArrayList<>()),
                new BookDTO(2L, "title2", new AuthorDTO(2L, "Hanna", "Banana"), "genre2", false, null, null,
                        new ArrayList<>())));

        // When
        List<BookDTO> actualResult = bookFlow.findAll();

        // Then
        verify(bookDataService, times(1)).findAll();
        assertThat(actualResult).containsExactlyInAnyOrder(
                new BookDTO(1L, "title", new AuthorDTO(1L, "Tom", "Someone"), "genre", false, null, null,
                        new ArrayList<>()),
                new BookDTO(2L, "title2", new AuthorDTO(2L, "Hanna", "Banana"), "genre2", false, null, null, new ArrayList<>()));
    }

    @Test
    void findAllBooksWithMatchingTitle() {
        // Given
        when(bookDataService.findByTitleContaining("title")).thenReturn(List.of(
                new BookDTO(1L, "title", new AuthorDTO(1L, "Tom", "Someone"), "genre", false, null, null, new ArrayList<>())));

        // When
        bookFlow.findByTitleContaining("title");

        // Then
        verify(bookDataService, times(1)).findByTitleContaining("title");
        assertThat(bookFlow.findByTitleContaining("title")).containsExactlyInAnyOrder(
                new BookDTO(1L, "title", new AuthorDTO(1L, "Tom", "Someone"), "genre", false, null, null, new ArrayList<>()));
    }
}
