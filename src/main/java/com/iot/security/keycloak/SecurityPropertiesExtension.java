package com.iot.security.keycloak;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("security.authz")
class SecurityPropertiesExtension {

	Map<String, List<String>> roleHierarchy = new LinkedHashMap<>();

	Map<String, List<String>> permissions = new LinkedHashMap<>();
}
