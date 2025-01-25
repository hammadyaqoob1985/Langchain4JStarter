package com.langchain4j.starter.config;


import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class RagConfig {

    @Bean
    EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    ApplicationRunner ingestDocs(
            EmbeddingModel embeddingModel,
            EmbeddingStore<TextSegment> embeddingStore,
            Tokenizer tokenizer,
            @Value("classpath:terms-of-service.txt") Resource termsOfService
    ) {

        return args -> {
            var doc = FileSystemDocumentLoader.loadDocument(termsOfService.getFile().toPath());
            var ingestor = EmbeddingStoreIngestor.builder()
                    .documentSplitter(DocumentSplitters.recursive(50, 0, tokenizer))
                    .embeddingModel(embeddingModel)
                    .embeddingStore(embeddingStore)
                    .build();

            ingestor.ingest(doc);
        };
    }
}
