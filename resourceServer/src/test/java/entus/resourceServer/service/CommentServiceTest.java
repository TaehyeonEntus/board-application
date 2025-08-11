package entus.resourceServer.service;

import entus.resourceServer.domain.Comment;
import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.add(User.createUser(1L));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add")
    void Add() throws Exception {
        //given
        User user = userService.get(1L);

        Long postId = postService.add(Post.createPost(user,"title","content"));
        Post post = postService.get(postId);

        //when
        Long commentId = commentService.add(Comment.createComment(user, post, "content"));

        //then
        assertNotNull(commentId);
    }

    @Test
    @DisplayName("Get")
    void Get() throws Exception {
        //given
        User user = userService.get(1L);

        Long postId = postService.add(Post.createPost(user,"title","content"));
        Post post = postService.get(postId);

        Long commentId = commentService.add(Comment.createComment(user, post, "content"));
        //when

        Comment comment = commentService.get(commentId);
        Long savedId = comment.getId();
        User savedUser = comment.getAuthor();
        Post savedPost = comment.getPost();

        //then
        assertEquals(commentId, savedId);
        assertEquals(user, savedUser);
        assertEquals(post, savedPost);
    }
}