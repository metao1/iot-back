package com.iot.security.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private DomainAwarePermissionEvaluator permissionEvaluator;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {

		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(permissionEvaluator);
		expressionHandler.setApplicationContext(applicationContext);

		return expressionHandler;
	}

	@Bean
	public RoleHierarchy roleHierarchy(SecurityPropertiesExtension spe) {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy(RoleHierarchyUtils.roleHierarchyFromMap(spe.getRoleHierarchy()));
		return roleHierarchy;
	}
}