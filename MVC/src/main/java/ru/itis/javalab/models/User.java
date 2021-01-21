package ru.itis.javalab.models;


import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private UUID cookieAuth;
    
}
