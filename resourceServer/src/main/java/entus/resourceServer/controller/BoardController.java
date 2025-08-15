package entus.resourceServer.controller;

import entus.resourceServer.domain.Comment;
import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.*;
import entus.resourceServer.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @ResponseBody
    @GetMapping("/api/board")
    public BoardDto apiBoard(@RequestParam(defaultValue = "0") int page) {
        Page<Post> postsList = postService.getPostsByPage(PageRequest.of(page, 20, Sort.by("id").descending()));
        List<Post> hotPostsList = postService.getTop5Posts();

        List<PostListDto> hotPosts = hotPostsList.stream()
                .map(PostListDto::new)
                .toList();

        List<PostListDto> posts = postsList.stream()
                .map(PostListDto::new)
                .toList();

        return new BoardDto(hotPosts, posts);
    }


    @GetMapping("/board/{postId}")
    public String viewPost(@PathVariable String postId, Model model) {
        postService.addViewCount(Long.parseLong(postId));
        model.addAttribute("postId", postId);
        if (!model.containsAttribute("commentAddDto")) {
            model.addAttribute("commentAddDto", new CommentAddDto());
        }
        return "viewPost";
    }

    @ResponseBody
    @GetMapping("/api/board/{postId}")
    public PostDto apiBoard(@PathVariable Long postId, Principal principal) {
        return new PostDto(postService.get(postId),Long.parseLong(principal.getName()));
    }

    @PostMapping("/board/{postId}/addComment")
    public String addComment(@PathVariable String postId,
                             @Valid @ModelAttribute CommentAddDto commentAddDto,
                             BindingResult bindingResult,
                             Principal principal,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("commentAddDto", commentAddDto);
            return "viewPost";
        }

        User user = userService.get(Long.parseLong(principal.getName()));
        Post post = postService.get(Long.parseLong(postId));
        commentService.add(Comment.createComment(user, post, commentAddDto.getComment()));

        return "redirect:/board/" + postId;
    }

    @GetMapping("/board/add")
    public String showAddPost(Model model) {
        if (!model.containsAttribute("postAddDto")) {
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
            model.addAttribute("postAddDto", postAddDto);
            return "addPost";
        }

        User user = userService.get(Long.parseLong(principal.getName()));
        Long postId = postService.add(Post.createPost(user, postAddDto.getTitle(), postAddDto.getContent()));

        return "redirect:/board/" + postId;
    }

    @PostMapping("/board/post/{postId}/like")
    @ResponseBody
    public PostDto likePost(@PathVariable Long postId,
                           Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        Post post = postService.like(postId, userId);

        return new PostDto(post,userId);
    }

    @PostMapping("/board/comment/{commentId}/like")
    @ResponseBody
    public CommentDto likeComment(@PathVariable Long commentId,
                                  Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        Comment comment = commentService.like(commentId, userId);

        return new CommentDto(comment,userId);
    }
}
