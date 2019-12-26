package com.iot.web.controller;

import com.iot.security.keycloak.PermissionResolver;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Secured("ROLE_API_CONSUMER")
@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class StatusController {

    private final PermissionResolver permissionResolver;

    @GetMapping("/current")
    public Object getUserInfo(@AuthenticationPrincipal KeycloakAuthenticationToken token) {
        Map<Object, Object> userInfo = new HashMap<>();

        userInfo.put("username", token.getName());
        userInfo.put("roles", token.getAuthorities().stream() //
                .map(GrantedAuthority::getAuthority) //
                .collect(Collectors.toList()) //
        );
        userInfo.put("permissions", permissionResolver.resolve(token));

        return userInfo;
    }
}
