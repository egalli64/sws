package com.example.sws17;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloCtr {
    @GetMapping("/hello")
    public String hello() {
        return "/hello";
    }
}
