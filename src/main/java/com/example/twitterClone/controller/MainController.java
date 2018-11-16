package com.example.twitterClone.controller;

import com.example.twitterClone.domain.Message;
import com.example.twitterClone.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    Repository repository;


    @GetMapping("/")
    public String greeting( Map<String, Object> model) {

        return "greeting";
    }
    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = repository.findAll();
        model.put("messages",messages);
        return "main";
    }

}