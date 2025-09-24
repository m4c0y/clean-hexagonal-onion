package com.fabriciooliveira.clean_hexagonal_onion.query.book;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;

public record BookView(String title, String genre, String author) {

    public BookView(BookDTO bookDTO, AuthorDTO authorDTO) {
        this(bookDTO.getTitle(), bookDTO.getGenre(), authorDTO.getFullName());
    }
}