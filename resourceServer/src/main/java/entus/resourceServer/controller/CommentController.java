package entus.resourceServer.controller;

import entus.resourceServer.domain.Comment;
import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import entus.resourceServer.dto.component.CommentAddRequestDto;
import entus.resourceServer.dto.component.CommentDto;
import entus.resourceServer.dto.page.CommentPageDto;
import entus.resourceServer.service.CommentService;
import entus.resourceServer.service.PostService;
import entus.resourceServer.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final UserService userService;
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentAddRequestDto dto, BindingResult bindingResult, Principal principal) {
        Map<String, String> errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        Long postId = dto.getPostId();
        Long userId = Long.parseLong(principal.getName());

        User user = userService.get(userId);
        Post post = postService.get(postId);
        String content = dto.getComment();

        commentService.add(Comment.createComment(user, post, content));

        Page<Comment> comments = commentService.getCommentsByPost(post, PageRequest.of(0, 10, Sort.by("id").descending()));

        List<CommentDto> commentsDto = comments.stream()
                .map(comment -> new CommentDto(comment, comment.isLikedBy(userId)))
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentPageDto(commentsDto,0,comments.getTotalPages()));
    }

    @PostMapping("/like/{commentId}")
    public ResponseEntity<?> likeComment(@PathVariable Long commentId, Principal principal) {
        Comment comment = commentService.get(commentId);
        User user = userService.get(Long.parseLong(principal.getName()));

        commentService.like(comment, user);

        Map<String, Object> response = new HashMap<>();

        response.put("commentId", commentId);
        response.put("userId", user.getId());

        return ResponseEntity.ok().body(response);
    }
}
