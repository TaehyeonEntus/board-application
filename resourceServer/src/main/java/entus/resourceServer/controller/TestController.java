package entus.resourceServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/public")
    public String publicTest(){
        return "public";
    }

    @GetMapping("/user")
    public String userTest(){
        return "user";
    }

    @GetMapping("/admin")
    public String adminTest() {
        return "admin";
    }
}
