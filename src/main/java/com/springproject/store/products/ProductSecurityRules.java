package com.springproject.store.products;

import com.springproject.store.common.SecurityRules;
import com.springproject.store.users.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;


//Do the same for all the other rules that exist in the Security Config class

@Component
public class ProductSecurityRules implements SecurityRules {
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry){

        registry.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/products/**").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/products/**").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole(Role.ADMIN.name());
    }

}
