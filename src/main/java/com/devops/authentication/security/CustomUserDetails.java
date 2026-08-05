package com.devops.authentication.security;

import com.devops.authentication.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles()
                .forEach(role -> {

                    authorities.add(
                            new SimpleGrantedAuthority(
                                    role.getName()
                            )
                    );


                    role.getPermissions()
                            .stream()
                            .filter(permission -> Boolean.TRUE.equals(permission.getActive()))
                            .forEach(permission -> {

                                authorities.add(
                                        new SimpleGrantedAuthority(
                                                permission.getName()
                                        )
                                );

                            });

                });

        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        return user.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }


    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }


    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}