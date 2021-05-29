package com.example.sws17;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloCtr {
    private static final Logger log = LogManager.getLogger(HelloCtr.class);

    @GetMapping("/hello")
    public String hello(@RequestParam boolean csrf) {
        log.traceEntry("hello: csrf is {}", csrf);

        return csrf ? "/hello" : "/helloUnsafe";
    }

    @PostMapping("/welcome")
    public String welcome(Model model, Authentication auth) {
        log.traceEntry("welcome");

        model.addAttribute("name", auth.getName());
        return "/welcome";
    }

    @PostMapping("/welcomeNoCsrf")
    public String welcomeUnsafe(Model model, Authentication auth) {
        log.traceEntry("welcomeUnsafe");

        model.addAttribute("name", auth.getName());
        return "/welcome";
    }

}
