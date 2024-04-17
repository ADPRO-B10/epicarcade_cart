package adpro.b10.epicarcade_functional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("")
public class FunctionalController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "HelloWorld2";
    }
}