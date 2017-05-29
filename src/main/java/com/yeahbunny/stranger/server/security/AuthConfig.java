package com.yeahbunny.stranger.server.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .requestMatcher(new AntPathRequestMatcher("/**"))
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/test/403").authenticated()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger**").permitAll()
                .anyRequest().permitAll()//to trzeba wykosic
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(getNotAuthorizedEntryPoint())
                .and()
                .csrf().disable();

        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication();
        }

        /**
         * Create Spring-session filter that replaces HttpSession with Spring implementation. Entry point for spring session
         * @return
         */
        @Bean
        SessionRepositoryFilter filter() {
            SessionRepositoryFilter filter = new SessionRepositoryFilter(sessionRepo());
            filter.setHttpSessionStrategy(sessionStrategy());
            return filter;
        }

        @Bean
        SessionRepository sessionRepo() {
            return new MapSessionRepository();
        }

        @Bean
        public HeaderHttpSessionStrategy sessionStrategy() {
            return new HeaderHttpSessionStrategy();
        }

        private static AuthenticationEntryPoint getNotAuthorizedEntryPoint() {
            return new CustomHttp403ForbiddenEntryPoint();
        }

}
