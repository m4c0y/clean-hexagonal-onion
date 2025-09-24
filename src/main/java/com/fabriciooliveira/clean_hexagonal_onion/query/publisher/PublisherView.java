package com.fabriciooliveira.clean_hexagonal_onion.query.publisher;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher.PublisherDTO;

import java.util.UUID;

public record PublisherView(UUID id, String name) {

    public PublisherView(PublisherDTO publisherDTO) {
        this(publisherDTO.getId(), publisherDTO.getName());
    }
}