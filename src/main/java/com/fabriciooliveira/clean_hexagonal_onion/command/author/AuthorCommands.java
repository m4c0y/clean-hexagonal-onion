package com.fabriciooliveira.clean_hexagonal_onion.command.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorFlow;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors/commands")
public class AuthorCommands {

    private final AuthorFlow authorFlow;

    public AuthorCommands(AuthorFlow authorFlow) {
        this.authorFlow = authorFlow;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void registerAuthor(@RequestBody RegisterAuthorDTO authorDTO) {
        authorFlow.registerAuthorByName(authorDTO.firstName(), authorDTO.lastName());
    }
}
