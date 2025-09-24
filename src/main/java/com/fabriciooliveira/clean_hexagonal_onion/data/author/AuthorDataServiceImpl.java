package com.fabriciooliveira.clean_hexagonal_onion.data.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDTO;
import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorDataServiceImpl implements AuthorDataService {

    private final AuthorRepository authorRepository;
    private final AuthorJPAMapper authorJPAMapper;

    public AuthorDataServiceImpl(AuthorRepository authorRepository, AuthorJPAMapper authorJPAMapper) {
        this.authorRepository = authorRepository;
        this.authorJPAMapper = authorJPAMapper;
    }

    @Override
    public void registerAuthor(AuthorDTO authorDTO) {
        authorRepository.save(authorJPAMapper.mapToJPA(authorDTO));
    }

    @Override
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(authorJPA -> new AuthorDTO(authorJPA.getId(), authorJPA.getFirstName(), authorJPA.getLastName()))
                .toList();
    }

    @Override
    public AuthorDTO findById(Long id) {
        var author = authorRepository.findById(id).orElseThrow();
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName());
    }

}
