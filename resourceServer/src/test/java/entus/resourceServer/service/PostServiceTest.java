package entus.resourceServer.service;

import entus.resourceServer.domain.Post;
import entus.resourceServer.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        userService.add(User.createUser(1L));
        userService.add(User.createUser(2L));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add")
    void Add() throws Exception {
        //given
        User user = userService.get(1L);
        //when
        Post post = Post.createPost(user, "title", "content");
        //then
        Long postId = postService.add(post);
        assertNotNull(postId);
    }

    @Test
    @DisplayName("Get")
    void Get() throws Exception {
        //given
        User user = userService.get(1L);
        Long postId = postService.add(Post.createPost(user, "title", "content"));

        //when
        Post post = postService.get(postId);

        //then
        assertEquals(post.getAuthor(), user);
        assertEquals(post.getTitle(), "title");
        assertEquals(post.getContent(), "content");
    }

    @Test
    @DisplayName("GetAll")
    void GetPostsByPage() throws Exception {
        //given
        User user1 = userService.get(1L);
        User user2 = userService.get(2L);

        postService.add(Post.createPost(user1, "title", "content"));
        postService.add(Post.createPost(user2, "title", "content"));

        //when
        Page<Post> posts = postService.getPostsByPage(PageRequest.of(0,20));

        //then
        assertEquals(posts.getTotalElements(), 2);
    }

    @Test
    @DisplayName("GetTop5Posts")
    void GetTop5Posts() throws Exception {
        //given
        User user = userService.get(1L);

        for(int i = 0; i<10; i++)
            postService.add(Post.createPost(user, "title", "content"));

        //when
        Page<Post> posts = postService.getPostsByPage(PageRequest.of(0, 20));
        List<Post> top5Posts = postService.getTop5Posts();

        //then
        assertEquals(posts.getTotalElements(),10);
        assertEquals(top5Posts.size(), 5);
    }
}