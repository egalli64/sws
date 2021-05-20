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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        log.trace("Authenticated in hello1 as " + name);
        model.addAttribute("name", name);

        return "/s12/hello";
    }

    @GetMapping("/s12/hello2")
    public String hello(Model model, Authentication auth) {
        String name = auth.getName();
        log.trace("Authenticated in hello2 as " + name);
        model.addAttribute("name", name);
        return "/s12/hello";
    }
}
