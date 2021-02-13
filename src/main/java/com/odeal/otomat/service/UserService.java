package com.odeal.otomat.service;

import com.odeal.otomat.dto.UserDto;
import com.odeal.otomat.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);
}
