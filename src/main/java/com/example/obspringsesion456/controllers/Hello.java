package com.example.obspringsesion456.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/saludo")
    public String saludo(){
        return "Hola, como estan?? Todo bien??";
    }
}
