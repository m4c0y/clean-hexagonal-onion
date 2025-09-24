package com.fabriciooliveira.clean_hexagonal_onion.data.book;

import com.fabriciooliveira.clean_hexagonal_onion.data.author.AuthorJPA;
import com.fabriciooliveira.clean_hexagonal_onion.domain.DomainEvent;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookJPA extends AbstractAggregateRoot<BookJPA> {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "book_seq_gen")
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private AuthorJPA author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "published")
    private boolean published;

    @Column(name = "publisher_id")
    private UUID publisherId;

    @Column(name = "isbn")
    private String isbn;

    public void registerDomainEvents(List<DomainEvent> domainEvents) {
        domainEvents.forEach(this::andEvent);
    }
}
