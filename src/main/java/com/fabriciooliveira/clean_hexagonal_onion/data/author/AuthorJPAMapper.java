package com.fabriciooliveira.clean_hexagonal_onion.data.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorJPAMapper {

    AuthorJPA mapToJPA(AuthorDTO author);

}
