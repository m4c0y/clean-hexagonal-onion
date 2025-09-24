package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author;

import com.fabriciooliveira.clean_hexagonal_onion.domain.author.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthorDomainMapperTest {

    @Autowired
    AuthorDomainMapper authorDomainMapper;

    @Test
    void mapToDomain() {
        // given
        var input = new AuthorDTO(1L, "first", "last");
        var expectedOutput = Author.builder()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .build();
        // when
        var result = authorDomainMapper.mapToDomain(input);
        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedOutput);
    }
}
