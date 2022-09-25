package com.springsecurity.spsecurity.service;

import com.springsecurity.spsecurity.dto.UserRegistrationDto;
import com.springsecurity.spsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService
{
    User save(UserRegistrationDto userRegistrationDto);

    List<User> showUser();
}
