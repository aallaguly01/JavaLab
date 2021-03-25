package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String hashPassword;
    private String confirmCode;

    @OneToMany(mappedBy = "owner")
    private List<Product> products;

    @Enumerated(value = EnumType.STRING)
    private State state;


    public enum State{
        ACTIVE, BANNED
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role{
        ADMIN, USER
    }

    public boolean isActive(){
        return this.state == State.ACTIVE;
    }

    public boolean isBanned(){
        return this.state == State.BANNED;
    }

    public boolean isAdmin(){
        return this.role == Role.ADMIN;
    }
}
