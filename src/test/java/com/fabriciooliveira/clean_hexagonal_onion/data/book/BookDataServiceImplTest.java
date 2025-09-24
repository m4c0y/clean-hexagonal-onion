package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorJPAMapper;
import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorJPAMapperImpl;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookDataServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    private BookJPAMapper bookJPAMapper;
    private AuthorJPAMapper authorJPAMapper;

    private BookDataServiceImpl bookDataServiceImpl;

    @BeforeEach
    void beforeAll() {
        authorJPAMapper = new AuthorJPAMapperImpl();
        bookJPAMapper = new BookJPAMapperImpl();
        bookJPAMapper.setAuthorJPAMapper(authorJPAMapper);
        bookDataServiceImpl = new BookDataServiceImpl(bookRepository, bookJPAMapper);
    }

    @Test
    void save() {
        // when
        bookDataServiceImpl.save(new BookDTO(1L, "title", new AuthorDTO(1L, null, null), "genre", false, null, null, new ArrayList<>()));
        // then
        verify(bookRepository, times(1)).save(any(BookJPA.class));
    }

    @Test
    void findAll() {
        // when
        bookDataServiceImpl.findAll();
        // then
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findByPartialTitle() {
        // when
        bookDataServiceImpl.findByTitleContaining("title");
        // then
        verify(bookRepository, times(1)).findByTitleContainingIgnoreCase("title");
    }
}
