package com.fabriciooliveira.clean_hexagonal_onion.domain.publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class Publisher {

    private UUID id;
    private String name;
}
