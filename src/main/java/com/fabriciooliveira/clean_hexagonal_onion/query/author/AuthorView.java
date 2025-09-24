package com.fabriciooliveira.clean_hexagonal_onion.query.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;

public record AuthorView(Long id, String name) {

    public AuthorView(AuthorDTO authorDTO) {
        this(authorDTO.getId(), authorDTO.getFullName());
    }
}
