package entus.resourceServer.controller;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.service.PostService;
import entus.resourceServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/public")
    @ResponseBody
    public String publicTest(){
        return "public";
    }

    @GetMapping("/user")
    @ResponseBody
    public String userTest(){
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String adminTest() {
        return "admin";
    }

    @GetMapping("/addTestData")
    public String addTestData(Principal principal){
        Long userId = Long.parseLong(principal.getName());
        System.out.println(userId);
        User user = userService.get(userId);
        postService.add(Post.createPost(user,"title","content"));
        return "redirect:/board";
    }
}
