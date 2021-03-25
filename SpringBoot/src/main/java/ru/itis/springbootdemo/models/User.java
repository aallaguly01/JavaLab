package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String hashPassword;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String confirmCode;

//    public enum State{
//        CONFIRMED, NOT_CONFIRMED
//    }

    public enum State{
        ACTIVE, BANNED
    }

    public enum Role{
        USER, ADMIN
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
