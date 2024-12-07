package com.xapp.member.authentication.configs;

//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

//https://godwin-pinto.medium.com/spring-security-handle-unauthorized-and-unauthenticated-requests-75715e7fc579
//Best
//https://docs.spring.io/spring-security/reference/reactive/configuration/webflux.html

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    //whitlisting swagger urls
    //https://github.com/springdoc/springdoc-openapi?tab=readme-ov-file#demo-spring-boot-2-webflux-with-openapi-3
    //http://server:port/context-path/swagger-ui.html
    //http://localhost:8951/auth/swagger-ui.html
    //https://springdoc.org/
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/auth/**"
    };


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges ->
                        exchanges.pathMatchers(AUTH_WHITELIST).permitAll() //No Need Authentication
                        .anyExchange().permitAll())
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/swagger-ui/**"))  //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/v3/api-docs/**")) //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/swagger-resources/**")) //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/configuration/ui")) //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/swagger-ui.html"))  //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/webjars/**"))       //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/v3/api-docs/**"))   //Need Authentication
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**")) //Need Authentication
                .httpBasic(withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable) //Disable csrf
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable);
        return http.build();
    }

    //User id and Password for Authentication if .anyExchange().authenticated() enabled
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("redhat")
                .roles("USER","ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

//    @Bean
//    public SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
//        http
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"))  // Match only /api/** endpoints
//                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated() ) // Require authentication for all /api/** requests
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt());  // Enable JWT authentication for OAuth2 resource server
//        return http.build();
//    }

}