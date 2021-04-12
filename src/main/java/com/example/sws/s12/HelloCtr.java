package com.example.sws.s12;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloCtr {
    private static final Logger log = LogManager.getLogger(HelloCtr.class);

    @GetMapping("/s12/hello1")
    public String hello(Model model) {
        log.traceEntry("hello");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", auth.getName());
        return "/s12/hello";
    }

    @GetMapping("/s12/hello2")
    public String hello(Model model, Authentication auth) {
        log.traceEntry("hello2");
        model.addAttribute("name", auth.getName());
        return "/s12/hello";
    }
}
