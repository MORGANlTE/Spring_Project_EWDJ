package com.example.ewdj;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/fifa", true);
		//redirecten naar fifa als het slaagt
		http.authorizeRequests().antMatchers("/*").hasRole("USER").and().csrf().and().authorizeRequests().antMatchers("/fifa/*").hasRole("ADMIN").and().csrf();
		
		
	} 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		PasswordEncoder encoder=  PasswordEncoderFactories.createDelegatingPasswordEncoder();// enable in memory based authentication with a user named// "user" and "admin“//use DelegatingPasswordEncoderfor //in-memory authentication
		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user")).roles("USER").and().withUser("admin").password(encoder.encode("admin")).roles("USER","ADMIN");
		//als we niet willen dat we de paswoorden encoderen doen we .password("{noop}user") <= dat zorgt ervoor dat je NoOpPasswordEndcoder gebruikt
	}
}
