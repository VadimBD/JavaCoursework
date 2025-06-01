package org.example.onlinestore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinestore.utils.Utils;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.keycloak.KeycloakPrincipal;

import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private Utils utils;
    @Autowired
    public  AuthController(Utils utils) {
        this.utils = utils;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String currentUrl = request.getRequestURL().toString();
        var keycloakLoginUrl=utils.getLoginUrl() + URLEncoder.encode(currentUrl, StandardCharsets.UTF_8);
       return  String.format("redirect:%s",keycloakLoginUrl);
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Principal principal) {

        // Получаем id_token напрямую из KeycloakSecurityContext
        String idToken = null;
        if (principal instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
            idToken = keycloakSecurityContext.getIdTokenString();
        }

        // Если есть idToken — отправляем запрос в Keycloak для логаута
        if (idToken != null) {
            String keycloakLogoutUrl = String.format(utils.getLogOutUrl(), idToken);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForEntity(keycloakLogoutUrl, Void.class);
        }

        // Завершаем сессию
        request.getSession().invalidate();

        // Перенаправляем обратно (или на главную)
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:"+referer;
        } else {
            return "redirect:/";
        }
    }
}
