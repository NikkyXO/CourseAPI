//package com.example.demo.authentication;
//
//import org.springframework.util.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.security.SecuritySchemes;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//// import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.models.security.SecurityScheme.Type;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//
//
//@Configuration
//@OpenAPIDefinition(info = @Info(title = "My API", version = "${api-version}",
//			contact = @Contact(name = "Olanike", email = "alexisdame2017@gmail.com"),
//			description = "${api.description}"),
//
//			security = {// @SecurityRequirement(name = "basicAuth"),
//					@SecurityRequirement(name = "bearerToken")})
//
//@SecuritySchemes({
//    // @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"), // name = "bearerAuth",
//    @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
//})
//
//
//public class OpenApiConfig {
//	private final String moduleName;
//	  private final String apiVersion;
//
//	  public OpenApiConfig(
//	      @Value("${module-name}") String moduleName,
//	      @Value("${api-version}") String apiVersion) {
//	    this.moduleName = moduleName;
//	    this.apiVersion = apiVersion;
//	  }
//
//	  @Bean
//	  public OpenAPI customOpenAPI() {
//	    final String securitySchemeName = "bearerAuth";
//	    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
//	    return new OpenAPI()
//	        .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
//	        		.addList(securitySchemeName))
//	        .components(
//	            new Components()
//	                .addSecuritySchemes(securitySchemeName,
//	                    new io.swagger.v3.oas.models.security.SecurityScheme()
//	                        .name(securitySchemeName)
//	                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
//	                        .scheme("bearer")
//	                        .bearerFormat("JWT")
//	                )
//	        )
//	        .info(new io.swagger.v3.oas.models.info.Info().title(apiTitle).version(apiVersion));
//	  }
//}
