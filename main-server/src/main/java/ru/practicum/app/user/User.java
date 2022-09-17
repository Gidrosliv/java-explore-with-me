package ru.practicum.app.user;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // TODO: 17.09.2022  убрать mb
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    String email;

}