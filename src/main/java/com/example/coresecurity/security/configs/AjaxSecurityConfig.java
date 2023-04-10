package com.example.coresecurity.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.coresecurity.security.common.AjaxLoginAuthenticationEntryPoint;
import com.example.coresecurity.security.filter.AjaxLoginProcessingFilter;
import com.example.coresecurity.security.hanlder.AjaxAccessDeniedHandler;
import com.example.coresecurity.security.hanlder.AjaxAuthenticationFailureHandler;
import com.example.coresecurity.security.hanlder.AjaxAuthenticationSuccessHandler;
import com.example.coresecurity.security.provider.AjaxAuthenticationProvider;

@Configuration
@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(ajaxAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.antMatcher("/api/**")
			.authorizeRequests()
			.antMatchers("/api/login").permitAll()
			.anyRequest().authenticated()
			// .and()
			// .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
		;

		http.exceptionHandling()
			.authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
			.accessDeniedHandler(ajaxAccessDeniedHandler())
			;

		http.csrf().disable();

		customConfigurerAjax(http);

	}

	private void customConfigurerAjax(HttpSecurity http) throws Exception {
		http
			.apply(new AjaxLoginConfigurer<>())
			.successHandlerAjax(ajaxAuthenticationSuccessHandler())
			.failureHandlerAjax(ajaxAuthenticationFailureHandler())
			.setAuthenticationManager(authenticationManagerBean())
			.loginProcessingUrl("/api/login");
	}

	// protected AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
	// 	AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter();
	// 	filter.setAuthenticationManager(authenticationManagerBean());
	// 	filter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
	// 	filter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
	// 	return filter;
	// }

	@Bean
	public AuthenticationProvider ajaxAuthenticationProvider() {
		return new AjaxAuthenticationProvider(passwordEncoder());
	}

	@Bean
	public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
		return new AjaxAuthenticationSuccessHandler();
	}

	@Bean
	public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
		return new AjaxAuthenticationFailureHandler();
	}

	@Bean
	public AjaxAccessDeniedHandler ajaxAccessDeniedHandler() {
		return new AjaxAccessDeniedHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}