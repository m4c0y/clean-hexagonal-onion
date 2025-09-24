package com.fabriciooliveira.clean_hexagonal_onion.query.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorFlow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorQueriesTest {

    @Mock
    private AuthorFlow authorFlow;

    @InjectMocks
    private AuthorQueries authorQueries;

    @Test
    void getAll() {
        // Given
        List<AuthorDTO> mockedAuthorListResponse = List.of(new AuthorDTO(1L, "firstName", "lastName"));
        when(authorFlow.getListOfAllAuthors()).thenReturn(mockedAuthorListResponse);

        // When & Then
        List<AuthorView> result = authorQueries.listAuthors();
        verify(authorFlow, times(1)).getListOfAllAuthors();
        assertThat(result).containsExactly(new AuthorView(1L, "firstName lastName"));
    }
}
