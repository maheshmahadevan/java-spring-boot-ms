package com.example.catalogserver;

import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import feign.Logger;
import feign.RequestInterceptor;

public class AuthConfigurationInterceptor {
	
		 
		 
		   @Bean
		   RequestInterceptor oauth2FeignRequestInterceptor() {
		      return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
		   }
		 
		   @Bean
		   Logger.Level feignLoggerLevel() {
		      return Logger.Level.FULL;
		   }
		 
		   private OAuth2ProtectedResourceDetails resource() {
			   ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		      resourceDetails.setClientId("ClientId");
		      resourceDetails.setClientSecret("secret");
		      resourceDetails.setAccessTokenUri("http://localhost:9090/auth/oauth/token");
		      //resourceDetails.setGrantType("client_credentials");
		      
		      return resourceDetails;
		   }
		 
		
}
