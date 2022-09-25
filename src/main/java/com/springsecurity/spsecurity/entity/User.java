package com.springsecurity.spsecurity.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
                name = "user_roles",
                joinColumns = @JoinColumn(
                                        name = "user_id", referencedColumnName = "id"),
                            inverseJoinColumns = @JoinColumn(
                                            name = "role_id" , referencedColumnName = "id")
                )

    private List<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
