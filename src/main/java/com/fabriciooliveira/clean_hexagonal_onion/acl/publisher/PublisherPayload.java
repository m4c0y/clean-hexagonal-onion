package com.fabriciooliveira.clean_hexagonal_onion.acl.publisher;

import java.util.UUID;

public record PublisherPayload(UUID id, String name) {
}
