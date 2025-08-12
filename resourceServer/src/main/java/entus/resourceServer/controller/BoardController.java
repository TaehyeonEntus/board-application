package entus.resourceServer.controller;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.BoardResponseDto;
import entus.resourceServer.domain.dto.PostAddDto;
import entus.resourceServer.domain.dto.PostDto;
import entus.resourceServer.domain.dto.PostListDto;
import entus.resourceServer.service.PostService;
import entus.resourceServer.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @ResponseBody
    @GetMapping("/api/board")
    public BoardResponseDto apiBoard(@RequestParam(defaultValue = "0") int page) {
        Page<Post> postsList = postService.getPostsByPage(PageRequest.of(page, 20, Sort.by("id").descending()));
        List<Post> hotPostsList = postService.getTop5Posts();

        List<PostListDto> hotPosts = hotPostsList.stream()
                .map(PostListDto::new)
                .toList();

        List<PostListDto> posts = postsList.stream()
                .map(PostListDto::new)
                .toList();

        return new BoardResponseDto(hotPosts, posts);
    }



    @GetMapping("/board/{postId}")
    public String viewPost(@PathVariable String postId) {
        return "viewPost";
    }

    @ResponseBody
    @GetMapping("/api/board/{postId}")
    public PostDto apiBoard(@PathVariable Long postId) {
        return new PostDto(postService.get(postId));
    }

    @GetMapping("/board/add")
    public String showAddPost(Model model) {
        System.out.println("model attributes: " + model.asMap());

        if(!model.containsAttribute("postAddDto")) {
            model.addAttribute("postAddDto", new PostAddDto());
        }
        return "addPost";
    }

    @GetMapping("/api/board/add")
    @ResponseBody
    public Map<String, String> apiBoardAdd() {
        Map<String, String> body = new HashMap<>();
        body.put("status", "ok");
        return body;  // 자동으로 JSON 변환됨
    }

    //thymeleaf가 valid하기 편해서... 여기는 thymeleaf rendering
    @PostMapping("/board/add")
    public String addPost(
            @Valid @ModelAttribute PostAddDto postAddDto,
            BindingResult bindingResult,
            Principal principal,
            Model model) {

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error);
            }
            model.addAttribute("postAddDto", postAddDto);
            return "addPost";
        }

        User user = userService.get(Long.parseLong(principal.getName()));
        Long postId = postService.add(Post.createPost(user, postAddDto.getTitle(), postAddDto.getContent()));

        return "redirect:/board/" + postId;
    }
}
