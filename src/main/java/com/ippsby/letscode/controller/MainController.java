package com.ippsby.letscode.controller;

import com.ippsby.letscode.domain.Message;
import com.ippsby.letscode.domain.User;
import com.ippsby.letscode.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private final MessageRepository messageRepository;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String greeting (Map<String , Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main (Map<String , Object> model){
        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages" , messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model){
        Message message = new Message(text, tag, user);

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages" , messages);

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter,  Map<String, Object> model){
        Iterable <Message> messages;
        if (filter !=null && !filter.isEmpty()) {
             messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.put("messages", messages);

        return "main";
    }


}
