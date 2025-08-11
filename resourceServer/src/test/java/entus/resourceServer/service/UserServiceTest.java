package entus.resourceServer.service;

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
    void Get() throws Exception {
        //given
        Long user1Id = 1L;
        Long user2Id = 2L;

        //when
        User user1 = User.createUser(user1Id);
        User user2 = User.createUser(user2Id);

        userService.add(user1);
        userService.add(user2);

        User savedUser1 = userService.get(user1Id);
        User savedUser2 = userService.get(user2Id);

        //then
        assertEquals(user1Id, savedUser1.getId());
        assertEquals(user2Id, savedUser2.getId());
    }

    @Test
    @DisplayName("GetAll")
    void GetAll() throws Exception {
        //given
        Long user1Id = 1L;
        Long user2Id = 2L;

        //when
        User user1 = User.createUser(user1Id);
        User user2 = User.createUser(user2Id);

        userService.add(user1);
        userService.add(user2);

        //then
        assertEquals(userService.getAll().size(),2);
    }

}