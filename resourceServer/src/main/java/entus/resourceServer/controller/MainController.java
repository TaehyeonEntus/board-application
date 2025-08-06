package entus.resourceServer.controller;

import entus.resourceServer.domain.Post;
import entus.resourceServer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PostService postService;
    @GetMapping("/")
    public String index(){
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String board(@RequestParam(defaultValue = "0") int page, Model model) {
        List<Post> hots = postService.getTop5Posts();
        Page<Post> posts = postService.getAll(PageRequest.of(page, 20, Sort.by("created_at").descending()));

        model.addAttribute("hots", hots);
        model.addAttribute("posts", posts);
        return "board";
    }
}
