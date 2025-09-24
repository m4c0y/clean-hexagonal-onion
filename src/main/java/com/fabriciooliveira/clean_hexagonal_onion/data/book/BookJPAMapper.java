package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorJPAMapper;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.book.BookDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookJPAMapper {

    @Autowired
    protected AuthorJPAMapper authorJPAMapper;

    public void setAuthorJPAMapper(AuthorJPAMapper authorJPAMapper) {
        this.authorJPAMapper = authorJPAMapper;
    }

    @Mapping(target = "author", expression = "java(authorJPAMapper.mapToJPA(bookDTO.getAuthorDTO()))")
    public abstract BookJPA mapToJPA(BookDTO bookDTO);

    @AfterMapping
    void registerDomainEvents(@MappingTarget BookJPA bookJPA, BookDTO bookDTO) {
        bookJPA.registerDomainEvents(bookDTO.getDomainEvents());
    }
}
