package com.neeraj.poc.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.io.*;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String sayHelloWorld() {
        return "Hello, World";
    }

    @GetMapping("/json/{name}")
    public Map<String, String> sayHelloWithName(@PathVariable String name) {
        return Map.of("Hello", name);
    }

}
