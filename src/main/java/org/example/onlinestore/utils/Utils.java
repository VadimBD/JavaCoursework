package org.example.onlinestore.utils;

import com.nimbusds.oauth2.sdk.id.Issuer;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class Utils {

    private ISettings settings;
    @Autowired
    public Utils( ISettings settings ) {
    this.settings = settings;
    }

    public String getCurrentUserId(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
            String userId = authToken.getPrincipal().getAttribute("sub"); // UUID пользователя
            return userId;
        }
        return null;
    }
    private String getKeycloakConfigKey(String key) {
    return STR."spring.security.oauth2.client.registration.keycloak.\{key}";
    }
    public String getLoginUrl() {
        return String.format(settings.getValue("keycloakloginUrlformat"),settings.getValue(getKeycloakConfigKey("client-id")),"/");
    }
    public  String  getLogOutUrl() {
        return String.format(settings.getValue("keycloaklogoutUrlformat"));
    }
}