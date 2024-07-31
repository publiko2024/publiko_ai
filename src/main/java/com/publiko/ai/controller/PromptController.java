package com.publiko.ai.controller;

import com.publiko.ai.service.PromptService;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping("/chat")
    public String getQueryResult(@RequestBody String query) {
        ChatResponse chatResponse = promptService.getChatResponse(query);
        return chatResponse.getResult().getOutput().getContent();
    }

    @PostMapping("/multimodal")
    public String getImageDescription(@RequestPart("file") @Nullable MultipartFile file) {
        return promptService.getImageDescription(file);
    }

}
