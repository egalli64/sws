package com.example.sws17;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloCtr {
    private static final Logger log = LogManager.getLogger(HelloCtr.class);

    @GetMapping("/hello")
    public String hello() {
        log.traceEntry();

        return "/hello";
    }

    @PostMapping("/welcome")
    public String welcome(Model model, Authentication auth) {
        log.traceEntry();

        model.addAttribute("name", auth.getName());
        return "/welcome";
    }

}
