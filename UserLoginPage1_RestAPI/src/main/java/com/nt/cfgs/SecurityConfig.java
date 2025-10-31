package com.nt.cfgs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nt.Service.ServiceImpl.UserLoginServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	 @Autowired
	  private UserLoginServiceImpl userDetailsService;
	 
	 @Bean
	 public BCryptPasswordEncoder encoder() {
		 return new BCryptPasswordEncoder(12);
	 }
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		 http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth ->auth.requestMatchers("/api/user/register","/api/user/login").permitAll()
				.anyRequest().authenticated()).httpBasic(httpBasic->{});
	return http.build();
	}
	 
	

//@Bean
//public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//	
// DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
// 
// provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
// provider.setUserDetailsService(userDetailsService);
// return provider;
//
//}
	 @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

}



