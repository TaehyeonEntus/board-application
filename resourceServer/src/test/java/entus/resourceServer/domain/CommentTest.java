package entus.resourceServer.domain;

import entus.resourceServer.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@DataJpaTest
class CommentTest {
    @Autowired
    CommentRepository commentRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("AddComment")
    void AddComment() throws Exception {
        //given
        String reply = "reply";
        User user = User.createUser(1L);
        Post post = Post.createPost(user,"Title","Content");

        //when
        Comment comment1 = Comment.addComment(reply,user,post);
        Comment comment2 = Comment.addComment(reply,user,post);

        //then
        assertNotSame(comment1, comment2);
    }

    @Test
    @DisplayName("GetId")
    void GetId() throws Exception {
        //given
        String reply = "reply";
        User user = User.createUser(1L);
        Post post = Post.createPost(user,"Title","Content");

        //when
        Comment comment1 = Comment.addComment(reply,user,post);
        Comment comment2 = Comment.addComment(reply,user,post);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        //then
        assertNotEquals(comment1.getId(), comment2.getId());
    }

    @Test
    @DisplayName("GetContent")
    void GetContent() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("GetAuthor")
    void GetAuthor() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("GetPost")
    void GetPost() throws Exception {
        //given

        //when

        //then

    }
}