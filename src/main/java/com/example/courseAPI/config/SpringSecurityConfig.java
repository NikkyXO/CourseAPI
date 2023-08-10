package com.example.courseAPI.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import com.example.courseAPI.entities.Permission;
import com.example.courseAPI.entities.Role;
import com.example.courseAPI.security.authentication.jwt.JwtAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {  // extends WebSecurityConfigurerAdapter {
	
	 private final JwtAuthenticationFilter jwtAuthFilter;
	 private final AuthenticationProvider authenticationProvider;
	 private final LogoutHandler logoutHandler;
	

	
	public static final String[] PUBLIC_PATHS = {"/api/auth/**",
	            "/v3/api-docs.yaml",
	            "/v3/api-docs/**",
	            "/swagger-ui/**",
	            "/ui/**",
	            "/docs/**",
	            "/swagger-ui.html",
	            "/api/v1/auth/**"
	          };
	
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        		.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        		.authorizeHttpRequests()
        		.requestMatchers(PUBLIC_PATHS).permitAll()
        		.requestMatchers("/api/v1/management/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
        		.requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.MANAGER_READ.name())
                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.MANAGER_CREATE.name())
                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.MANAGER_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.MANAGER_DELETE.name())

                      
                .anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	              .and()
	              .authenticationProvider(authenticationProvider)
	              .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	              .logout()
	              .logoutUrl("/api/v1/auth/logout")
	              .addLogoutHandler(logoutHandler)
	              .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
          ;

          return http.build();
    }
  
    
}
