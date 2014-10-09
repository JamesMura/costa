package com.github.jamesmura.costa.web.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "com.github.jamesmura.costa.web.models.User.findAll",
                query = "SELECT u FROM User u"
        ),
        @NamedQuery(
                name = "com.github.jamesmura.costa.web.models.User.findById",
                query = "SELECT u FROM User u WHERE u.id = :id"
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Size(min = 7, max = 100, message = "Password Should be at least 7 characters")
    @Column(nullable = false)
    private String password;

    @NotNull
    @Email
    @Column(nullable = false)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
