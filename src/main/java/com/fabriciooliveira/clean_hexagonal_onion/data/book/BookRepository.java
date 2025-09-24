package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookJPA, Long> {

    @Query("SELECT b FROM BookJPA b WHERE UPPER(b.title) LIKE CONCAT('%',UPPER(:title),'%')")
    List<BookJPA> findByTitleContainingIgnoreCase(@Param("title") String title);
}
