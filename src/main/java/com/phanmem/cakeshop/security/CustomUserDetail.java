package com.phanmem.cakeshop.security;

import com.phanmem.cakeshop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail extends User implements UserDetails {
    public CustomUserDetail(User user){
        super(user);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList= new ArrayList<>();
        super.getRoles().forEach(role -> {
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
        });
        return  grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return super.getPassWord();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }
}
