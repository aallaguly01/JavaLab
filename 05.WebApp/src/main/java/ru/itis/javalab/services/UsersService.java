package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    List<User> getAllUsers();

    List<UserDto> getAllUsers(int page, int size);

    void addUser(UserDto userDto);

    boolean signIn(String email, String password, UUID uuid);

    boolean checkAuthCookie(String authCookie);
}
