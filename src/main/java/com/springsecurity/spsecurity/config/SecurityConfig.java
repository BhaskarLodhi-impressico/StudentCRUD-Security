package com.springsecurity.spsecurity.config;

import com.springsecurity.spsecurity.service.impl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception
    {
        http.authorizeRequests().antMatchers("/registration**",
                                                        "js/**",
                                                        "/css/**",
                                                        "/html/**"
                                             ).permitAll().anyRequest().authenticated().
                                            and().formLogin().loginPage("/login").permitAll().
                                            and().logout().invalidateHttpSession(true).clearAuthentication(true).
                                            logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                                            logoutSuccessUrl("/login?logout").permitAll();
    }
}
