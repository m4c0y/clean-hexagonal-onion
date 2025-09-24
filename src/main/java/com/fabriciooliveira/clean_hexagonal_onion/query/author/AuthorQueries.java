package com.fabriciooliveira.clean_hexagonal_onion.query.author;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.author.AuthorFlow;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorQueries {

    private final AuthorFlow authorFlow;

    public AuthorQueries(AuthorFlow authorFlow) {
        this.authorFlow = authorFlow;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AuthorView> listAuthors() {
        return authorFlow.getListOfAllAuthors().stream()
                .map(AuthorView::new)
                .toList();
    }
}
