package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author;

import java.util.List;

public interface AuthorDataService {

    void registerAuthor(AuthorDTO author);

    List<AuthorDTO> findAll();

    AuthorDTO findById(Long id);
}
