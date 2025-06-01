package org.example.onlinestore.utils;

import com.nimbusds.oauth2.sdk.id.Issuer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    private ISettings settings;
    @Autowired
    public Utils( ISettings settings ) {
    this.settings = settings;
    }

    public String getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof OidcUser oidcUser) {
            // Возвращает уникальный ID пользователя из токена (claim "sub")
            return oidcUser.getSubject();
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