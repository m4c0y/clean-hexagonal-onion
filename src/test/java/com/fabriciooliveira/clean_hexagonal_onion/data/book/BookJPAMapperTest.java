package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorJPAMapper;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookJPAMapperTest {

    @Autowired
    BookJPAMapper bookJPAMapper;

    @Autowired
    AuthorJPAMapper authorJPAMapper;

    @Test
    void mapToJPA() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "firstName", "lastName");
        BookJPA bookJPA = bookJPAMapper.mapToJPA(new BookDTO(1L, "title", authorDTO, "genre",
                false, null, null, new ArrayList<>()));
        assertThat(bookJPA).usingRecursiveComparison().isEqualTo(BookJPA.builder()
                .id(1L)
                .title("title")
                .author(authorJPAMapper.mapToJPA(authorDTO))
                .genre("genre")
                .isbn(null)
                .published(false)
                .publisherId(null)
                .build());
    }
}