package it.unicam.coloni.hackhub.context.identity.infrastructure.security;


import it.unicam.coloni.hackhub.context.identity.application.utility.JWTHelper;
import it.unicam.coloni.hackhub.context.identity.infrastructure.security.filters.JwtFilter;
import it.unicam.coloni.hackhub.context.identity.infrastructure.security.providers.JWTProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {



    private final DatabaseUserDetailsService userDetailsService;

    public SecurityConfig(DatabaseUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            .csrf(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/user/**").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .addFilterAfter(filter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    JwtFilter filter(){
        return new JwtFilter();
    }


    @Bean
    AuthenticationManager provider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(encoder());

        JWTProvider jwt = new JWTProvider();

        List<AuthenticationProvider> providers= new ArrayList<>();
        providers.add(dao);
        providers.add(jwt);

        return new ProviderManager(providers);

    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);

    }

    @Bean
    public JWTHelper jwtHelper(){
        return new SpringJWTHelper();
    }




}