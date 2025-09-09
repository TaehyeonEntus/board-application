package entus.resourceServer.controller;

import entus.resourceServer.domain.Comment;
import entus.resourceServer.domain.Post;
import entus.resourceServer.dto.component.CommentDto;
import entus.resourceServer.dto.component.PostDto;
import entus.resourceServer.dto.component.PostSummaryDto;
import entus.resourceServer.dto.page.BoardPageDto;
import entus.resourceServer.dto.page.CommentPageDto;
import entus.resourceServer.dto.page.PostPageDto;
import entus.resourceServer.service.CommentService;
import entus.resourceServer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ApiController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/board")
    public BoardPageDto apiBoard(@RequestParam(defaultValue = "0") int page) {
        Page<Post> postsList = postService.getPosts(PageRequest.of(page, 20, Sort.by("id").descending()));
        List<Post> hotPostsList = postService.getTop5Posts();

        List<PostSummaryDto> hotPostsDto = hotPostsList.stream()
                .map(PostSummaryDto::new)
                .toList();

        List<PostSummaryDto> postsDto = postsList.stream()
                .map(PostSummaryDto::new)
                .toList();

        return new BoardPageDto(hotPostsDto, postsDto, page, postsList.getTotalPages());
    }

    //Post + Comment
    @GetMapping("/board/{postId}")
    public ResponseEntity<?> apiPostAndComments(@PathVariable Long postId, Principal principal){
        Long userId = Long.parseLong(principal.getName());
        Post post;
        try {
            post = postService.getAndAddViewCount(postId);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "존재하지 않는 게시글입니다."));
        }
        Page<Comment> comments = commentService.getCommentsByPost(post, PageRequest.of(0, 10, Sort.by("id").descending()));

        PostDto postDto = new PostDto(post, post.isLikedBy(userId));
        List<CommentDto> commentsDto = comments
                        .stream()
                        .map(comment -> new CommentDto(comment, comment.isLikedBy(userId)))
                        .toList();

        return ResponseEntity.ok().body(new PostPageDto(postDto, commentsDto, 0, comments.getTotalPages()));
    }

    //Post만
    @GetMapping("/board/{postId}/post")
    public PostDto apiPost(@PathVariable Long postId, Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        Post post = postService.get(postId);

        return new PostDto(post, post.isLikedBy(userId));
    }

    //Comments만
    @GetMapping("/board/{postId}/comment")
    public ResponseEntity<?> apiComments(@RequestParam(defaultValue = "0") int page, @PathVariable Long postId, Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        Post post = postService.get(postId);
        Page<Comment> comments = commentService.getCommentsByPost(post, PageRequest.of(page, 10, Sort.by("id").descending()));


        List<CommentDto> commentsDto = comments.stream()
                .map(comment -> new CommentDto(comment, comment.isLikedBy(userId)))
                .toList();

        return ResponseEntity.ok().body(new CommentPageDto(commentsDto,page,comments.getTotalPages()));
    }
}
