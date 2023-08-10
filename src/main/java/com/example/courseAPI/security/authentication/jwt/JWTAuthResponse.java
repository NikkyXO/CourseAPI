package com.example.courseAPI.security.authentication.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	
	private String accessToken;
	
}
