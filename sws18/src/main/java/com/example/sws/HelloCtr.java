package com.example.sws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCtr {
    private static final Logger log = LogManager.getLogger(HelloCtr.class);

    @GetMapping("/hello")
    public Hello hello() {
        log.traceEntry("hello");

        return new Hello("Have a good day!");
    }
}