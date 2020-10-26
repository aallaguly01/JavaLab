package ru.itis.javalab.models;


import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString

public class Auth {
    private Long id;
    private String login;
    private String password;
}
