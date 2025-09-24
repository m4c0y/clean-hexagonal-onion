package com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher;

import com.fabriciooliveira.clean_hexagonal_onion.domain.publisher.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    private UUID id;
    private String name;

    public PublisherDTO(Publisher publisher) {
        this(publisher.getId(), publisher.getName());
    }
}
