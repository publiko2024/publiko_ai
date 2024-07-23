package com.publiko.ai.controller;

import com.publiko.ai.service.EmbeddingService;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    @PostMapping("/embedding")
    public void EmbeddingText(@RequestBody String text) {
        embeddingService.saveEmbedding(text);
    }

    @PostMapping("/embedding/pdf")
    public void EmbeddingPdf(@RequestPart("file") @Nullable MultipartFile file) throws IOException {
        embeddingService.savePdfEmbedding(Objects.requireNonNull(file));
    }
}
