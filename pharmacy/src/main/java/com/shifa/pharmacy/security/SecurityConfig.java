package com.shifa.pharmacy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/users/userRole/**").hasRole("ADMIN")

                // .antMatchers("/buses/list").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/testing").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/").permitAll()

                .antMatchers("/users/register").permitAll()
                .antMatchers("/user/register").permitAll()


                .antMatchers("/login").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/patients/register").permitAll()
                .antMatchers("/drugs/create").permitAll()
                .antMatchers("/drugs/add").permitAll()
                .antMatchers("/drugs/detail/**").permitAll()
                .antMatchers("/drug/details").permitAll()
                .antMatchers("/drugs/edit/**").permitAll()
                .antMatchers("/drug/edit").permitAll()
                .antMatchers("/drugs/list").permitAll()

                .antMatchers("/categories/list").permitAll()
                .antMatchers("/categories/create").permitAll()
                .antMatchers("/categories/add").permitAll()

                .antMatchers("/companies/add").permitAll()
                .antMatchers("/companies/list").permitAll()
                .antMatchers("/patients/create").permitAll()
                .antMatchers("/companies/edit/**").permitAll()
                .antMatchers("/companies/create").permitAll()
                .antMatchers("/branches/list").permitAll()
                .antMatchers("/branches/create").permitAll()
                .antMatchers("/branches/add").permitAll()
                .antMatchers("/branches/edit/**").permitAll()
                .antMatchers("/branches/update").permitAll()
                .antMatchers("/branches/delete/**").permitAll()




                .antMatchers("/about us").permitAll()
                .antMatchers("/js/sweetalert2.all.min").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/", true)
                //.failureUrl("/login.html?error=true")
                //.failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                //.logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");
        //.logoutSuccessHandler(logoutSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**", "/fontawesome/**", "/css/**", "/images/**", "/jquery/**", "/js/**");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

