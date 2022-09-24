package com.example.fpbmexamensurveiallnce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping("/saoudi")
    public String saoudi()
    {
        return "Ya Halla!";
    }
}
