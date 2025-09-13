package entus.resourceServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(){
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String board() {
        return "home";
    }

    @GetMapping("/board/{postId}")
    public String viewPost(@PathVariable String postId) {
        return "viewPost";
    }

    @GetMapping("/post/add")
    public String showAddPost() {
        return "addPost";
    }
}
