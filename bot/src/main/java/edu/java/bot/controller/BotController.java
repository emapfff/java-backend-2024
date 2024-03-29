package edu.java.bot.controller;

import dto.LinkUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BotController {
    @PostMapping("/updates")
    @Operation(summary = "Отправить обновление", description = "Обновление обработано")
    @ApiResponse(responseCode = "200", description = "Обновление обработано")
    public void sendUpdates(@RequestBody LinkUpdateRequest linkUpdateRequest) {
        log.info("Send updates!");
    }
}
