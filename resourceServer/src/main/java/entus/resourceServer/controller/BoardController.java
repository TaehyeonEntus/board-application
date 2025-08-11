package entus.resourceServer.controller;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.BoardResponseDto;
import entus.resourceServer.domain.dto.PostAddDto;
import entus.resourceServer.domain.dto.PostDto;
import entus.resourceServer.domain.dto.PostListDto;
import entus.resourceServer.service.PostService;
import entus.resourceServer.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<Post> hotPostsList = postService.getTop5Posts();
        Page<Post> postsList = postService.getAll(PageRequest.of(page, 20, Sort.by("id").descending()));

        List<PostListDto> hotPosts = hotPostsList.stream()
                .map(PostListDto::new)
                .toList();

        List<PostListDto> posts = postsList.stream()
                .map(PostListDto::new)
                .toList();

        return new BoardResponseDto(hotPosts, posts);
    }

    @GetMapping("/board/{postId}")
    public String board(@PathVariable Long postId, Model model) {
        Post post = postService.get(postId);

        PostDto postDto = new PostDto(post);

        model.addAttribute("post", postDto);
        return "viewPost";
    }

    @GetMapping("/board/add")
    public String showAddPost() {
        return "addPost";
    }

    @PostMapping("/board/add")
    public String addPost(@ModelAttribute PostAddDto dto, HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        User user = userService.get(userId);

        Long postId = postService.add(Post.createPost(user, dto.getTitle(), dto.getContent()));

        return "redirect:/board/" + postId;
    }
}
