package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.javalab.dto.UserDto.from;


public class UserServicesImpl implements UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UserServicesImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        return from(usersRepository.findAll(page, size));
    }

    @Override
    public void addUser(UserDto userDto) {
        usersRepository.save(User.builder()
                .age(18)
                .firstName(userDto.getFirstname())
                .lastName(userDto.getLastname())
                .build());
    }

    @Override
    public boolean signIn(String email, String password, UUID uuid) {
//        usersRepository.findOneByEmail(email).ifPresent(user -> {
//            if (password.equals(user.getPassword())){
//                UUID uuid = UUID.randomUUID();
//                System.err.println(uuid);
//                usersRepository.updateAuthCookie(uuid, email);
//            }
//
//        });

//        passwordEncoder.matches(password, usersRepository.findOneByEmail(email).get().getPassword())
        if (usersRepository.findOneByEmail(email).isPresent()){
            if (password.equals(usersRepository.findOneByEmail(email).get().getPassword())){
                usersRepository.updateAuthCookie(uuid, email);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkAuthCookie(String authCookie) {
        return usersRepository.checkAuthCookie(authCookie);
    }
}
