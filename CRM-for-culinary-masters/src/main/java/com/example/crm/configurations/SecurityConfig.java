package com.example.crm.configurations;

import com.example.crm.services.CustomUserDetailsService;
import com.example.crm.services.Interceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final CustomUserDetailsService userDetailsService;
    private final Interceptor interceptor;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor).addPathPatterns("/api/user");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();

//                ).formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/", true)
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .permitAll()
//                                .logoutSuccessUrl("/")
        return http.build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
}