package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book;

import java.util.List;

public interface BookDataService {

    void save(BookDTO bookDTO);

    BookDTO findById(Long id);

    List<BookDTO> findAll();

    List<BookDTO> findByTitleContaining(String title);

    class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String errorMessage) {
            super(errorMessage);
        }
    }
}
