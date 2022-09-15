package ru.practicum.app.user;

import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    HttpStatus delete(long userId);

    List<UserDto> getUser(String[] ids, Integer from, Integer size);
}