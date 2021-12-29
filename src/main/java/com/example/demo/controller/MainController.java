package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(value = "http://localhost:3000", maxAge = 1800, allowedHeaders = "*")
public class MainController {

    @GetMapping(value = "/")
    @ResponseBody

    public boolean index() {
        return true;
    }
}