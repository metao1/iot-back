package com.iot.security.keycloak;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class DefaultPermissionResolver implements PermissionResolver {

	private final SecurityPropertiesExtension securityPropertiesExtension;

	@Override
	public Set<String> resolve(Authentication authentication) {
		return authentication.getAuthorities().stream() //
				.flatMap(this::permissionsForRole) //
				.collect(Collectors.toSet());
	}

	private Stream<String> permissionsForRole(GrantedAuthority authority) {
		return new HashSet<>(securityPropertiesExtension.getPermissions().getOrDefault(authority.getAuthority(),
				Collections.emptyList())).stream();
	}

}