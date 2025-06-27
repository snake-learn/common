package com.snake.common.securities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserDetails implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";

    String username;

    String sub;

    Long iat;

    Long exp;

    transient String password;

    private Set<String> roles;

    private Set<String> permissions;

    @Builder.Default
    boolean isAccountNonExpired = true;

    @Builder.Default
    boolean isAccountNonLocked = true;

    @Builder.Default
    boolean isCredentialsNonExpired = true;

    @Builder.Default
    boolean isEnabled = true;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        val result = new ArrayList<GrantedAuthority>();

        if (CollectionUtils.isNotEmpty(this.roles)) {
            for (var role : this.roles) {
                if (role.startsWith(ROLE_PREFIX)) {
                    result.add(() -> role);
                } else {
                    result.add(() -> ROLE_PREFIX + role);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(this.permissions)) {
            for (var permission : this.permissions) {
                result.add(() -> permission);
            }
        }

        return result;
    }
}
