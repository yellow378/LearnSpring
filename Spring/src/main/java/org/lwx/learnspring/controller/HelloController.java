package org.lwx.learnspring.controller;

import org.lwx.learnspring.annotations.Loggable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @Loggable
    public String hello(){
        return "hello world";
    }

}
