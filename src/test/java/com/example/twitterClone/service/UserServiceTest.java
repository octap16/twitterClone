package com.example.twitterClone.service;

import com.example.twitterClone.domain.Role;
import com.example.twitterClone.domain.User;
import com.example.twitterClone.repository.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void addUser() {
        User user = new User();

        user.setEmail("some@email@gmail.com");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo,Mockito.times(1)).save(user);
        Mockito.verify(mailSender,Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }
    @Test
    public void addUserFailTest(){
        User user = new User();

        user.setEmail("Mykola");
        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("Mybkola");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Mockito.verify(userRepo,Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        Mockito.verify(mailSender,Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }
}