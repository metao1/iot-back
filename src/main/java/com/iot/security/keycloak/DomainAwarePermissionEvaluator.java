package com.iot.security.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
class DomainAwarePermissionEvaluator implements PermissionEvaluator {

	private final PermissionResolver permissionResolver;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

		log.info("check permission '{}' for user '{}' for target '{}'", permission, authentication.getName(),
				targetDomainObject);

		Set<String> givenPermissions = permissionResolver.resolve(authentication);
		Set<String> requiredPermissions = toPermissions(permission);

		boolean permissionsMatch = givenPermissions.containsAll(requiredPermissions);
		if (!permissionsMatch) {
			log.debug("Insufficient permissions:\nRequired: {}\nGiven: {}", requiredPermissions, givenPermissions);

			return false;
		}

		// Delegate to bounded context specific permission evaluation...
		if ("place-order".equals(permission)) {
				return hasRole("ROLE_ADMIN", authentication);
		}

		return true;
	}

	private Set<String> toPermissions(Object permission) {

		if (permission instanceof String) {
			return Collections.singleton((String) permission);
		}

		// TODO deal with other forms of required permissions...
		return Collections.emptySet();
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
								 Object permission) {
		return hasPermission(authentication, new DomainObjectReference(targetId, targetType), permission);
	}

	private boolean hasRole(String role, Authentication auth) {

		if (auth == null || auth.getPrincipal() == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		if (CollectionUtils.isEmpty(authorities)) {
			return false;
		}

		return authorities.stream().anyMatch(ga -> role.equals(ga.getAuthority()));
	}

	@Value
	static class DomainObjectReference {
		private final Serializable targetId;
		private final String targetType;
	}
}
