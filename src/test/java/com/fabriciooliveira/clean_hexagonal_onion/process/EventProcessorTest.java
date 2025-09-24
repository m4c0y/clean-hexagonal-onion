package com.fabriciooliveira.clean_hexagonal_onion.process;

import com.fabriciooliveira.clean_hexagonal_onion.domain.book.Book;
import com.fabriciooliveira.clean_hexagonal_onion.process.book.PublishBookDelegate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
@SpringBootTest
class EventProcessorTest {

    @Mock
    private PublishBookDelegate publishBookDelegate;

    @InjectMocks
    private EventProcessor eventProcessor;

    @Test
    void shouldCallTheDelegateToActOnEvent() {
        // When
        Book.RequestPublishingEvent requestPublishingEvent = new Book.RequestPublishingEvent(1L, UUID.randomUUID());
        eventProcessor.handleEvent(requestPublishingEvent);

        // Then
        verify(publishBookDelegate, times(1)).publishBook(requestPublishingEvent);
    }
}
