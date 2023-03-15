package com.mycapstone.masonsoccer.security;

import com.mycapstone.masonsoccer.models.AuthGroup;
import com.mycapstone.masonsoccer.models.Coach;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserPrincipal implements UserDetails {

    private Coach coach;
    private List<AuthGroup> authGroup;

    public AppUserPrincipal(Coach coach, List<AuthGroup> authGroup) {
        this.coach = coach;
        this.authGroup = authGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authGroup.stream().map(auth -> new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {

        return coach.getPassword();
    }

    @Override
    public String getUsername() {
        return coach.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
