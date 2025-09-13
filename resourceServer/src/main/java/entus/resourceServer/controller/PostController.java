package entus.resourceServer.controller;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.dto.request.PostAddRequestDto;
import entus.resourceServer.service.PostService;
import entus.resourceServer.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@Valid @RequestBody PostAddRequestDto dto, BindingResult bindingResult, Principal principal) {
        Map<String, String> errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + " " + fieldError.getDefaultMessage());
            }
            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        Long userId = Long.parseLong(principal.getName());

        User user = userService.get(userId);
        String title = dto.getTitle();
        String content = dto.getContent();

        Long postId = postService.add(Post.createPost(user, title, content));

        Map<String, Object> response = new HashMap<>();
        response.put("postId", postId);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<?> likeComment(@PathVariable Long postId, Principal principal) {
        Post post = postService.get(postId);
        User user = userService.get(Long.parseLong(principal.getName()));

        postService.like(post, user);

        Map<String, Object> response = new HashMap<>();

        response.put("postId", postId);
        response.put("userId", user.getId());

        return ResponseEntity.ok().body(response);
    }
}
