package com.example.task_manager.config;

import com.example.task_manager.security.jwt.JwtAuthEntryPoint;
import com.example.task_manager.security.jwt.JwtConfigurer;
import com.example.task_manager.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;


    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String AUTH_ENDPOINT = "/api/auth/*";
    private static final String USER_ENDPOINT = "/api/user/*";
    private static final String MANAGER_ENDPOINT = "/api/manager/*";

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .antMatchers(MANAGER_ENDPOINT).hasAnyRole("PM","DM")
                .anyRequest().authenticated();

        httpSecurity.apply(new JwtConfigurer(jwtTokenProvider));
    }

}
