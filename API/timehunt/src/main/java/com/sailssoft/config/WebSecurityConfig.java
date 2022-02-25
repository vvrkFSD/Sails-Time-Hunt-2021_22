package com.sailssoft.config;


import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.sailssoft.service.AppUserService;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    
//    	 final String[] AUTH_WHITELIST = {
//    	            // -- Swagger UI v2
//    	            "/v2/api-docs",
//    	            "/swagger-resources",
//    	            "/swagger-resources/**",
//    	            "/configuration/ui",
//    	            "/configuration/security",
//    	            "/swagger-ui.html",
//    	            "/webjars/**",
//    	            // -- Swagger UI v3 (OpenAPI)
//    	            "/v3/api-docs/**",
//    	            "/swagger-ui/**",
//    	            "/api/v*/forgot_password/**",
//    	            "/api/v*/login/**",
//    	            "/api/v*/logout/**"
//    	            
//    	            // other public endpoints of your API may be appended to this array
//    	    };
//    	 
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers(AUTH_WHITELIST)
//                    .permitAll()
//                    .antMatchers("/api/v*/home")
//            		.hasAuthority("USER")
//            		.antMatchers("/api/v*/admin")
//            		.hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated().and()
//                .formLogin()
//                .loginPage("/api/v*/login")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v*/logout"))
//                .logoutSuccessUrl("/api/v*/login?logout")
//                .permitAll();
//                    
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v*/forgot_password/**")
                    .permitAll()
                    .antMatchers("/api/v*/home/*")
            		.hasAuthority("USER")
            		.antMatchers("/api/v*/admin/*")
            		.hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
