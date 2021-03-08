package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.otus.spring.dao.UserDao;
import ru.otus.spring.domain.User;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optUser = userDao.findOneByLogin(login);

        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException(login);
        }

        return optUser.get();
    }
}
