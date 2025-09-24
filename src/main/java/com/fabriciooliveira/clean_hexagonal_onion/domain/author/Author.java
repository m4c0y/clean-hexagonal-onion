package com.fabriciooliveira.clean_hexagonal_onion.domain.author;

import com.fabriciooliveira.clean_hexagonal_onion.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Author {

    private Long id;
    private String firstName;
    private String lastName;

    public static Author createAuthor(String firstName, String lastName) {
        return new Author(null, firstName, lastName);
    }

    public Book writeBook(String title, String gender) {
        return Book.createBook(title, gender, this);
    }
}
