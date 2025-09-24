package com.fabriciooliveira.clean_hexagonal_onion.data.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthorJPAMapperTest {

    @Autowired
    AuthorJPAMapper authorJPAMapper;

    @Test
    void mapToJPA() {
        // Given
        AuthorDTO input = new AuthorDTO(1L, "first", "last");
        AuthorJPA expectedOutput = AuthorJPA.builder()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .build();

        // When
        AuthorJPA result = authorJPAMapper.mapToJPA(input);

        // Then
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedOutput);
    }
}
