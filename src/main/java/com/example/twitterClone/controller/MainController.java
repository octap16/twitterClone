package com.example.twitterClone.controller;

import com.example.twitterClone.domain.Message;
import com.example.twitterClone.domain.User;
import com.example.twitterClone.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    Repository repository;

    @Value("${upload.path}")
    private String uploadPath;

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
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
          @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);
    if(bindingResult.hasErrors()){
        Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
        model.mergeAttributes(errorsMap);
        model.addAttribute("message", message);
    }else {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }

        model.addAttribute("messsage",null);
        repository.save(message);
    }
        Iterable<Message> messages = repository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }



    @PostMapping ("filter")
    public String filter(@RequestParam String filter,  Map<String, Object> model) {
        List<Message> messages = repository.findByTag(filter);
        model.put("messages",messages);
        return "main";
    }
}