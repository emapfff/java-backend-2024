package edu.java.bot.service;

import dto.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotQueueConsumer implements Listener {
    private final UpdateService updateService;

    @KafkaListener(
        topics = "${app.scrapper-topic.name}",
        groupId = "group_id",
        containerFactory = "listenerContainerFactory"
    )
    @Override
    public void listen(@Payload @NotNull LinkUpdateRequest linkUpdateRequest) {
        log.info("Kafka producer has sent message!");
        try {
            log.info("{} {}", linkUpdateRequest.description(), linkUpdateRequest.url());
            updateService.sendUpdate(linkUpdateRequest);
        } catch (Exception e) {
            log.debug("DLQ activated!!!");
        }
    }
}
