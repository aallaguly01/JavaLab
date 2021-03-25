package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();
}
