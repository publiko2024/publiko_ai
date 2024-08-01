package com.publiko.ai.controller;

import com.publiko.ai.service.PromptService;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping("/chat")
    public String getQueryResult(@RequestBody String query) {
        log.info(query);
        ChatResponse chatResponse = promptService.getChatResponse(query);
        return chatResponse.getResult().getOutput().getContent();
    }

    @PostMapping("/chat-stream")
    public Flux<String> getQueryResultStream(@RequestBody String query) {
        return promptService.getChatResponseStream(query);
    }

    @PostMapping("/multimodal")
    public String getImageDescription(@RequestPart("file") @Nullable MultipartFile file) {
        return promptService.getImageDescription(file);
    }

}
