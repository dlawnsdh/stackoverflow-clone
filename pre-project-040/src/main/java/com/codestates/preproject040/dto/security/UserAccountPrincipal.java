package com.codestates.preproject040.dto.security;

import com.codestates.preproject040.dto.user.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record UserAccountPrincipal(
        String username,
        String userPassword,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

    public static UserAccountPrincipal of(String username, String userPassword, String email, String nickname) {
        return of(username, userPassword, email, nickname, Map.of());
    }

    public static UserAccountPrincipal of(String username, String userPassword, String email, String nickname, Map<String, Object> oAuth2Attributes) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new UserAccountPrincipal(
                username,
                userPassword,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                oAuth2Attributes
        );
    }

    public static UserAccountPrincipal from(UserAccountDto dto) {
        return UserAccountPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                username,
                userPassword,
                email,
                nickname
        );
    }

    @Override public String getName() { return username; }
    @Override public String getPassword() { return userPassword; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public Map<String, Object> getAttributes() { return oAuth2Attributes; }
    @Override public String getUsername() { return username; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public enum RoleType {
        USER("ROLE_USER");

        @Getter private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }

}
