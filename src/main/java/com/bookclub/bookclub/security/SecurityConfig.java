package com.bookclub.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
public PasswordEncoder passwordEncoder() {
 return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}

@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder) {
 return new InMemoryUserDetailsManager(
     User.withUsername("user").password(encoder.encode("password")).roles("USER").build(),
     User.withUsername("adminuser").password(encoder.encode("adminpass")).roles("USER","ADMIN").build()
 );
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 http
   .authorizeHttpRequests(auth -> auth
     .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
     .anyRequest().authenticated()                 // <-- protects "/"
   )
   .formLogin(form -> form
     .loginPage("/login")
     .defaultSuccessUrl("/", true)
     .permitAll()
   )
   .logout(l -> l
     // you’re using a GET link to /logout in the header:
     .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
     .logoutSuccessUrl("/login?logout")
     .permitAll()
   )
   .csrf(Customizer.withDefaults());
 return http.build();
}
}
