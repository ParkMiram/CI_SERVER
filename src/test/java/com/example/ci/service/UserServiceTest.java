package com.example.ci.service;

import com.example.ci.domain.entity.User;
import com.example.ci.domain.repository.UserRepository;
import com.example.ci.dto.request.UserRequest;
import com.example.ci.dto.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void getUsers() {
        BDDMockito.given(userRepository.findAll()).willReturn(
                List.of(new User(1L,"test1", 20),new User(2L,"test2", 20)));

        List<UserResponse> all = userService.getUsers();

        assertEquals(2, all.size());
        assertEquals("test1", all.get(0).name());
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void getUserById() {
        BDDMockito.given(userRepository.findById(1L)).willReturn(
                Optional.of(new User(1L, "test1", 20))
        );

        UserResponse user = userService.getUserById(1L);

        assertEquals("test1", user.name());
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    void insertUser() {
        UserRequest userRequest = new UserRequest(1L,"test1", 20);
        Mockito.when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        userService.insertUser(userRequest);

        assertEquals("test1", userRequest.name());
        assertEquals(20, userRequest.age());

        Mockito.verify(userRepository).save(any(User.class));
    }

    @Test
    void deleteUserById() {
        BDDMockito.doNothing().when(userRepository).deleteById(1L);
        BDDMockito.given(userRepository.findById(1L)).willReturn(
                Optional.empty()
        );

        userService.deleteById(1L);

        assertEquals(Optional.empty(), userRepository.findById(1L));
    }
}