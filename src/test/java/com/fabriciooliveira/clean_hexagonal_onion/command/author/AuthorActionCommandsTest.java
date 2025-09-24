package com.fabriciooliveira.clean_hexagonal_onion.command.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorFlow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorActionCommandsTest {

    @Mock
    private AuthorFlow authorFlow;

    @InjectMocks
    private AuthorActionCommands authorActionCommands;

    @Test
    void writeBook() {
        // Given
        var writeBookPayload = new RegisterBookDTO("title", "genre");

        // When
        authorActionCommands.writeBook(1L, writeBookPayload);

        // Then
        verify(authorFlow, times(1)).writeBook(1L, "title", "genre");
    }
}
