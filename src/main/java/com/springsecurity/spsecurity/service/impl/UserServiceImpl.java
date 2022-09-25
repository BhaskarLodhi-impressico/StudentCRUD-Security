package com.springsecurity.spsecurity.service.impl;

import com.springsecurity.spsecurity.dto.UserRegistrationDto;
import com.springsecurity.spsecurity.entity.Role;
import com.springsecurity.spsecurity.entity.User;
import com.springsecurity.spsecurity.repository.UserRepository;
import com.springsecurity.spsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto)
    {
        User user = new User(userRegistrationDto.getFirstName(),
                            userRegistrationDto.getLastName(),
                            userRegistrationDto.getEmail(),
                            bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                            Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public List<User> showUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
           User user = userRepository.findByEmail(username);
           if(user == null)
           {
               throw new UsernameNotFoundException("Invalid Username or Password");
           }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
