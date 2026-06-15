package com.example.actuator;

import org.springframework.modulith.events.EventPublication;
import org.springframework.modulith.events.CompletedEventPublications;

import org.springframework.modulith.events.IncompleteEventPublications;

import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventRegistryController {

    private final IncompleteEventPublications incompleteEvents;
    private final CompletedEventPublications  completeEvents;

    // Modulith 1.2 automatically wires your JDBC/H2 bean into this interface
    public EventRegistryController(IncompleteEventPublications incompleteEvents,
                                   CompletedEventPublications  completeEvents) {
        this.incompleteEvents = incompleteEvents;
        this.completeEvents = completeEvents;
    }

    public record CompletedEventDto(
            String eventType,
            String listenerId,
            Instant publicationDate,
            Instant completionDate
    ) {}

    @GetMapping("/completed")
    public List<CompletedEventDto> getCompletedEvents() {
        return completeEvents.findAll().stream()
                .map(pub -> {
                    return new CompletedEventDto(
                            pub.getEvent().getClass().getName(),          // Safe String representation
                            pub.getApplicationEvent().toString(), // Listener identity
                            pub.getPublicationDate(),                     // When it started
                            pub.getCompletionDate().orElse(null)          // When it finished
                    );
                })
                .collect(Collectors.toList());
    }
    @PostMapping("/retry")
    public void retryStuckEvents() {

        incompleteEvents.resubmitIncompletePublicationsOlderThan(Duration.ofMinutes(5));
    }
}