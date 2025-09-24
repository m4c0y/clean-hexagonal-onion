package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorFlowTest {

    @Mock
    private AuthorDataService authorDataService;

    @InjectMocks
    private AuthorFlow authorFlow;

    @Test
    void registerAuthorByName() {
        // Given
        var expectedAuthor = new AuthorDTO(null, "firstName", "lastName");
        ArgumentCaptor<AuthorDTO> argumentCaptor = ArgumentCaptor.forClass(AuthorDTO.class);

        // When
        authorFlow.registerAuthorByName("firstName", "lastName");

        // Then
        verify(authorDataService, times(1)).registerAuthor(argumentCaptor.capture());
        AuthorDTO actualAuthor = argumentCaptor.getValue();
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getListOfAllAuthors() {
        // Given
        var authorsData = List.of(
                new AuthorDTO(1L, "firstName1", "lastName1"),
                new AuthorDTO(2L, "firstName2", "lastName2"));
        when(authorDataService.findAll()).thenReturn(authorsData);

        // When
        List<AuthorDTO> actualResult = authorFlow.getListOfAllAuthors();

        // Then
        assertThat(actualResult).containsExactlyInAnyOrder(new AuthorDTO(1L, "firstName1", "lastName1"),
                new AuthorDTO(2L, "firstName2", "lastName2"));
    }
}