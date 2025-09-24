package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book;

import com.fabriciooliveira.clean_hexagonal_onion.domain.book.Book;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookDomainMapper {

    @Autowired
    protected AuthorDomainMapper authorDomainMapper;

    @Mapping(target = "author", expression = "java(authorDomainMapper.mapToDomain(bookDTO.getAuthorDTO()))")
    public abstract Book mapToDomain(BookDTO bookDTO);
}
