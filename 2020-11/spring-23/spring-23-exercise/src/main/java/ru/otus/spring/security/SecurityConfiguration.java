package ru.otus.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.spring.dao.UserDao;
import ru.otus.spring.domain.User;

import java.util.List;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDao userDao;

    public SecurityConfiguration(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
         .csrf().disable()
         .authorizeRequests().antMatchers("/library").permitAll()
         .and()
         .authorizeRequests().antMatchers("/deleteBook", "/editBook", "/insertBook").authenticated()
         .and()
         .formLogin()
         .and()
         .rememberMe()
         .key("MyAppSecret")
         .tokenValiditySeconds(60);
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {

        List<User> userList = userDao.findAll();
        for (int i=0; i < userList.size(); i++) {
            User tmpUser = userList.get(i);
            auth.inMemoryAuthentication()
                    .withUser( tmpUser.getLogin() ).password( tmpUser.getPassword() ).roles( "ADMIN" ); // !!! РОЛИ НАСТРОИТЬ

        }
    }


}
