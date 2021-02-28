package com.example.MessangerServer.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// Представляет необходимую информацию для построение обьекта Authentication
public class JwtUser implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final boolean enabled;
    // Отражает разрешения(роли) выданые пользователю
    private final Collection<? extends GrantedAuthority> authorities;

    // Конструктор пользователя
    public JwtUser(Long id, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId (){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Указание срока истечения пользователя
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Указание заблокированого пользователя
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Указание срока истечения данных пользователя
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Проверка заблокированости пользователя
    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
