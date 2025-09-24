package com.fabriciooliveira.clean_hexagonal_onion.query.book;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorFlow;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookFlow;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookQueries {

    private final BookFlow bookFlow;
    private final AuthorFlow authorFlow;

    public BookQueries(BookFlow bookFlow, AuthorFlow authorFlow) {
        this.bookFlow = bookFlow;
        this.authorFlow = authorFlow;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<BookView> listBooks(@RequestParam(value = "title", required = false) String title) {
        if (title == null || title.isEmpty()) {
            return bookFlow.findAll().stream()
                    .map(bookDTO -> new BookView(bookDTO, getBookAuthor(bookDTO)))
                    .toList();
        }

        return bookFlow.findByTitleContaining(title).stream()
                .map(bookDTO -> new BookView(bookDTO, getBookAuthor(bookDTO)))
                .toList();
    }

    private AuthorDTO getBookAuthor(BookDTO bookDTO) {
        return authorFlow.getAuthorById(bookDTO.getAuthorDTO().getId());
    }
}
