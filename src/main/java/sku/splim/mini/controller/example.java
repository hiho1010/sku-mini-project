package sku.splim.mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class example {
    @GetMapping("/api")
    void hello() {
        System.out.println("hello");
    }
}
