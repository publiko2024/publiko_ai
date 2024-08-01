package com.publiko.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PromptService {

    private final ChatModel chatModel;
    private final VectorStore vectorStore;

    public ChatResponse getChatResponse(String query) {

        final OpenAiChatOptions options = OpenAiChatOptions.builder()
            .withModel("gpt-4")
            .withTemperature(0.2F).build();

        return ChatClient.builder(chatModel)
            .build().prompt()
            .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
            .user(query).options(options)
            .call()
            .chatResponse();
    }

    public Flux<String> getChatResponseStream(String query) {

        final OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel("gpt-4")
                .withTemperature(0.2F).build();

        return ChatClient.builder(chatModel)
                .build().prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
                .user(query).options(options)
                .stream()
                .content();
    }

    public Flux<String> getImageDescriptionStream(MultipartFile file) {
        return ChatClient.create(chatModel).prompt()
                .user(u -> u.text("이 사진에서 무엇이 보이는지 설명해 줄래?")
                        .media(MimeTypeUtils.IMAGE_PNG, file.getResource()))
                .stream()
                .content();
    }

    public String getImageDescription(MultipartFile file) {
        return ChatClient.create(chatModel).prompt()
            .user(u -> u.text("이 사진에서 무엇이 보이는지 설명해 줄래?")
                .media(MimeTypeUtils.IMAGE_PNG, file.getResource()))
            .call()
            .content();
    }
}
