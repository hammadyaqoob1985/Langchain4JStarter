package com.langchain4j.starter.service;

import org.springframework.stereotype.Service;

@Service
public class AssistantService {

    private final Assistant assistant;

    public AssistantService(Assistant assistant) {
        this.assistant = assistant;
    }


    public String chat(String userMessage) {
        return assistant.chat(userMessage);
    }
}
