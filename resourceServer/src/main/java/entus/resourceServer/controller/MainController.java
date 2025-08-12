package entus.resourceServer.controller;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.service.PostService;
import entus.resourceServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final PostService postService;
    private final UserService userService;
    @GetMapping("/")
    public String index(){
        return "redirect:/board";
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
