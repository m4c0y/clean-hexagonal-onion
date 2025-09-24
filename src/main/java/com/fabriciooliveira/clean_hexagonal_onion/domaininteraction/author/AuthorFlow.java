package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author;

import com.fabriciooliveira.clean_hexagonal_onion.domain.author.Author;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorFlow {

    private final AuthorDataService authorDataService;
    private final BookDataService bookDataService;
    private final AuthorDomainMapper authorDomainMapper;

    public AuthorFlow(AuthorDataService authorDataService, BookDataService bookDataService, AuthorDomainMapper authorDomainMapper) {
        this.authorDataService = authorDataService;
        this.bookDataService = bookDataService;
        this.authorDomainMapper = authorDomainMapper;
    }

    public void registerAuthorByName(String firstName, String lastName) {
        Author author = Author.createAuthor(firstName, lastName);
        authorDataService.registerAuthor(new AuthorDTO(author));
    }

    public List<AuthorDTO> getListOfAllAuthors() {
        return authorDataService.findAll();
    }

    public AuthorDTO getAuthorById(Long id) {
        return authorDataService.findById(id);
    }

    public void writeBook(Long authorId, String title, String gender) {
        var author = authorDomainMapper.mapToDomain(authorDataService.findById(authorId));
        var book = author.writeBook(title, gender);
        bookDataService.save(new BookDTO(book));
    }
}
