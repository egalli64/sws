package com.example.sws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeCtrl {
    private static final Logger log = LogManager.getLogger(HomeCtrl.class);

    @GetMapping
    int answer() {
        log.traceEntry("answer");
        return 42;
    }
}
