package com.langchain4j.starter.controller;

import com.langchain4j.starter.dto.UserInput;
import com.langchain4j.starter.service.AssistantService;
import dev.langchain4j.data.message.ChatMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/langchain")
public class langchainController {

    private final AssistantService assistantService;

    public langchainController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @CrossOrigin
    @PostMapping("/chat")
    public String chat(@RequestBody UserInput userInput) {
        return assistantService.chat(userInput.userMessage());
    }

}
