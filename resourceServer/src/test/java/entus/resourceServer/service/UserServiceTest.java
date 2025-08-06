package entus.resourceServer.service;

import entus.resourceServer.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add")
    void Add() throws Exception {
        //given
        Long userId = 1L;

        //when
        User user = User.createUser(1L);
        Long savedId = userService.add(user);

        //then
        assertEquals(savedId, userId);
    }

    @Test
    @DisplayName("Get")
    @Transactional
    void Get() throws Exception {
        //given
        Long userId = 1L;

        //when
        User user1 = userService.get(userId);
        User user2 = userService.get(userId);

        //then
        assertSame(user1, user2);
    }

    @Test
    @DisplayName("GetAll")
    void GetAll() throws Exception {
        //given
        int totalUser = 1;
        //when
        List<User> users = userService.getAll();
        //then
        assertEquals(totalUser, users.size());
    }

    @Test
    @DisplayName("SyncUser")
    void SyncUser() throws Exception {
        //given

        //when

        //then

    }
}