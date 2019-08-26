package com.devox.app.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final int id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String phone;
    private final String district;
    private final String language;
    private final String email;
    private final String parentid;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
            int id,
            String username,
            String firstname,
            String lastname,
            String phone, String district, String language, String email,
            String password, String parentid, Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.district = district;
        this.language = language;
        this.email = email;
        this.password = password;
        this.parentid = parentid;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getDistrict() {
        return district;
    }

    public String getParentid() {
        return parentid;
    }
}
