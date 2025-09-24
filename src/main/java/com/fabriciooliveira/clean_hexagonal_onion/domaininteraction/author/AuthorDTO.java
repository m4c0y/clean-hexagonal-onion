package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author;

import com.fabriciooliveira.clean_hexagonal_onion.domain.author.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;

    public AuthorDTO(Author author) {
        this(author.getId(), author.getFirstName(), author.getLastName());
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
