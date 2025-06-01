package org.example.onlinestore.config;

import org.example.onlinestore.model.Cart;
import org.example.onlinestore.utils.ISettings;
import org.example.onlinestore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@EnableTransactionManagement
@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private Environment env;
    @Bean
    public Cart cart(){
        return new Cart();
    }
    @Bean
    public ISettings settings(){
        return new ApplicationProperties();
    }
    @Bean
    public Utils utils(){
        return new Utils(settings());
    }
    @Bean
    public ApplicationProperties applicationProperties(){
        return new ApplicationProperties();
    }
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
