package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDataServiceImpl implements BookDataService {

    private final BookRepository bookRepository;
    private final BookJPAMapper bookJPAMapper;

    public BookDataServiceImpl(BookRepository bookRepository, BookJPAMapper bookJPAMapper) {
        this.bookRepository = bookRepository;
        this.bookJPAMapper = bookJPAMapper;
    }

    @Override
    public void save(BookDTO bookDTO) {
        bookRepository.save(bookJPAMapper.mapToJPA(bookDTO));
    }

    @Override
    public BookDTO findById(Long id) {
        return bookRepository.findById(id)
                .map(this::getDTOFromJPA)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id %d could not be found!", id)));
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(this::getDTOFromJPA).toList();

    }

    @Override
    public List<BookDTO> findByTitleContaining(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::getDTOFromJPA)
                .toList();
    }

    private BookDTO getDTOFromJPA(BookJPA bookJPA) {
        var authorJPA = bookJPA.getAuthor();
        var authorDTO = new AuthorDTO(authorJPA.getId(), authorJPA.getFirstName(), authorJPA.getLastName());
        return new BookDTO(bookJPA.getId(), bookJPA.getTitle(), authorDTO, bookJPA.getGenre(),
                bookJPA.isPublished(), bookJPA.getPublisherId(), bookJPA.getIsbn(), new ArrayList<>());
    }
}
