package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author;

import com.fabriciooliveira.clean_hexagonal_onion.domain.author.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDomainMapper {

    Author mapToDomain(AuthorDTO authorDTO);

}
