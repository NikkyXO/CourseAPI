package com.example.demo.authentication.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       try {
    	   	if (request.getServletPath().contains("/api/auth/signup")) {
    	   		  System.out.print("Here firstly.....");
    		      filterChain.doFilter(request, response);
    		      return; 
    		} else {
    			String token = getTokenFromRequest(request);
    			
    	        // validate token
    	        if(token != null && jwtTokenProvider.validateToken(token)){
    	
    	            // get userName from token
    	            String username = jwtTokenProvider.getUsername(token);
    	
    	            // load the user associated with token
    	            UserDetails userDetail = userDetailsService.loadUserByUsername(username);
    	
    	            var authenticationToken = new UsernamePasswordAuthenticationToken(
    	                userDetail,
    	                null,
    	                userDetail.getAuthorities());
    	
    	            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    	
    	            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    	        }
    		}
	        
        } catch (Exception e) {
        	logger.error("Cannot set user autjentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request){

        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}

