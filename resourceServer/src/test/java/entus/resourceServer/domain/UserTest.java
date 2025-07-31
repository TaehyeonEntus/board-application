package entus.resourceServer.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("유저 생성 정적 메서드")
    void createUser() throws Exception {
        //given
        Long user1Id = 1L;
        Long user2Id = 2L;
        //when
        User user1 = new User(user1Id);
        User user2 = new User(user2Id);
        //then
        assertNotSame(user1, user2);
    }

    @Test
    @DisplayName("getter -> id")
    void getId() throws Exception {
        //given
        Long user1Id = 1L;
        Long user2Id = 2L;
        //when
        User user1 = User.createUser(user1Id);
        User user2 = User.createUser(user2Id);

        //then
        assertEquals(user1Id, user1.getId());
        assertEquals(user2Id, user2.getId());
    }

    @Test
    @DisplayName("getter -> posts")
    void getPosts() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("getter -> comment")
    void getComments() throws Exception {
        //given

        //when

        //then

    }
}