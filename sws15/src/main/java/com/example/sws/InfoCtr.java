package com.example.sws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InfoCtr {
    @GetMapping("/info/{id}")
    public String hello(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        return "/info";
    }
}
