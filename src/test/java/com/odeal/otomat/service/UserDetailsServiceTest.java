package com.odeal.otomat.service;

import com.odeal.otomat.entity.User;
import com.odeal.otomat.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserDetailsServiceTest {
    private final String SOME_STRING = "someString";
    private final Long SOME_LONG = 1L;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDetails userDetails;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setEmail(SOME_STRING);
        user.setName(SOME_STRING);
        user.setPassword(SOME_STRING);
        user.setSurname(SOME_STRING);
        user.setUsername(SOME_STRING);
        user.setId(SOME_LONG);
        user.setCreatedDate(new Date());
    }

    @Test
    void loadUserByUsernameToPass(){
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(modelMapper.map(user, UserDetails.class)).thenReturn(userDetails);
    }
}
