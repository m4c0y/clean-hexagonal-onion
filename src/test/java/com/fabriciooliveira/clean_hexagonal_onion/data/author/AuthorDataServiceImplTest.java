package com.fabriciooliveira.clean_hexagonal_onion.data.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorDataServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorJPAMapper authorJPAMapper;

    private AuthorDataServiceImpl authorService;

    @BeforeEach
    void beforeAll() {
        authorJPAMapper = new AuthorJPAMapperImpl();
        authorService = new AuthorDataServiceImpl(authorRepository, authorJPAMapper);
    }

    @Test
    void shouldCallRepository() {
        // When
        authorService.registerAuthor(new AuthorDTO(1L, "firstName", "lastName"));

        // Then
        ArgumentCaptor<AuthorJPA> argumentCaptor = ArgumentCaptor.forClass(AuthorJPA.class);
        verify(authorRepository, times(1)).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(
                AuthorJPA.builder()
                        .id(1L)
                        .firstName("firstName")
                        .lastName("lastName")
                        .build());
    }
}
