package com.publiko.ai.controller;

import com.publiko.ai.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping("/chat")
    public String getQueryResult(@RequestBody String query) {
        ChatResponse chatResponse = promptService.getChatResponse(query);
        return chatResponse.getResult().getOutput().getContent();
    }

}
