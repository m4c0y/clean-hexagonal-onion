package com.fabriciooliveira.clean_hexagonal_onion.command.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorFlow;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors/{id}/commands")
public class AuthorActionCommands {

    private final AuthorFlow authorFlow;

    public AuthorActionCommands(AuthorFlow authorFlow) {
        this.authorFlow = authorFlow;
    }

    @PostMapping("/writeBook")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void writeBook(@PathVariable("id") Long authorId, @RequestBody RegisterBookDTO registerBookDTO) {
        authorFlow.writeBook(authorId, registerBookDTO.title(), registerBookDTO.gender());
    }

}
