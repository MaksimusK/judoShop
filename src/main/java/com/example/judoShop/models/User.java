package com.example.judoShop.models;

import com.example.judoShop.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "email", unique = true)
    private  String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image avatar;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER
    ,mappedBy = "users")
    private List<Product> products = new ArrayList<>();

    @Column(name = "password", length = 1000)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private LocalDateTime dateOfCreate;

    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    //security


    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //Срок действия учетной записи
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    //Учетная запись не заблокирована
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    //Срок действия сертификата не истек
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    //Активен
    @Override
    public boolean isEnabled() {
        return active;
    }
}
