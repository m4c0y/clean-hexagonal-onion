package com.fabriciooliveira.clean_hexagonal_onion.query.publisher;

import com.fabriciooliveira.clean_hexagonal_onion.domaininteraction.publisher.PublisherAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("publishers")
public class PublisherQueries {

    private final PublisherAppService publisherAppService;

    public PublisherQueries(PublisherAppService publisherAppService) {
        this.publisherAppService = publisherAppService;
    }

    @GetMapping
    List<PublisherView> getAllPublishers() {
        return publisherAppService.getAllPublishers().stream()
                .map(PublisherView::new)
                .toList();
    }
}
