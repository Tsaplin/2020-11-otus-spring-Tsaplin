package ru.otus.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
          // По умолчанию SecurityContext хранится в сессии. Эта часть вырубает и каждый запросом приходит
         .csrf().disable()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
         .and()
         .authorizeRequests().antMatchers("/library").permitAll()
         .and()
         .authorizeRequests().antMatchers( "/deleteBook", "/editBook", "/insertBook").authenticated()
         .and()
         .formLogin()
         .and()
         .logout().logoutUrl("/error");

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
                    .withUser( tmpUser.getLogin() ).password( tmpUser.getPassword() ).roles( tmpUser.getRole() );

        }
    }


}
