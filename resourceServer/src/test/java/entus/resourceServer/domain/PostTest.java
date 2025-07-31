package entus.resourceServer.domain;

import entus.resourceServer.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostTest {
    @Autowired
    PostRepository postRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("게시글 생성 정적 메서드")
    void createPost() throws Exception {
        //given
        User user = User.createUser(1L);

        String title = "title";
        String content = "content";

        //when
        Post post1 = Post.createPost(user,title,content);
        Post post2 = Post.createPost(user,title,content);

        //then
        assertNotSame(post1, post2);
    }

    @Test
    @DisplayName("getter -> id")
    void getId() throws Exception {
        //given
        User user1 = User.createUser(1L);
        User user2 = User.createUser(2L);

        String title = "title";
        String content = "content";

        //when
        Post post1 = Post.createPost(user1,title,content);
        Post post2 = Post.createPost(user2,title,content);

        postRepository.save(post1);
        postRepository.save(post2);

        //then
        assertNotEquals(post1.getId(),post2.getId());
    }

    @Test
    @DisplayName("getter -> id")
    void getAuthor() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("getter -> id")
    void getTitle() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("getter -> id")
    void getContent() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("getter -> id")
    void getComments() throws Exception {
        //given

        //when

        //then

    }
}