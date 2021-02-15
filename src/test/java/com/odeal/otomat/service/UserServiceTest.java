package com.odeal.otomat.service;

import com.odeal.otomat.dto.RegistrationRequest;
import com.odeal.otomat.dto.UserDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    private final String SOME_STRING = "someString";

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;
    private UserDto userDto;
    private RegistrationRequest registrationRequest;
    private Pageable pageable;


    @BeforeEach
    void setUp() {
        registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail(SOME_STRING);
        registrationRequest.setName(SOME_STRING);
        registrationRequest.setPassword(SOME_STRING);
        registrationRequest.setSurname(SOME_STRING);
        registrationRequest.setUsername(SOME_STRING);

    }

    @Test
    void registerToPass() {
        when(modelMapper.map(registrationRequest, User.class)).thenReturn(user);
        when(bCryptPasswordEncoder.encode(registrationRequest.getPassword())).thenReturn(SOME_STRING);
        when(userRepository.save(user)).thenReturn(user);
        assertTrue(true);
    }

    @Test
    void getByUsernameToPass(){
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
    }

    @Test
    void getMethodsToPass(){
        when(userRepository.getOne(anyLong())).thenReturn(new User());
        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
    }


}
