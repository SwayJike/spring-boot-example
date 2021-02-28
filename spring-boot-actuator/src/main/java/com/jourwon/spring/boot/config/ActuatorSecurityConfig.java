package com.jourwon.spring.boot.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置
 *
 * @author JourWon
 * @date 2021/2/27
 */
@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * This spring security configuration does the following
     * <p>
     * 1. Restrict access to the Shutdown endpoint to the ACTUATOR_ADMIN role.
     * 2. Allow access to all other actuator endpoints.
     * 3. Allow access to static resources.
     * 4. Allow access to the home page (/).
     * 5. All other requests need to be authenticated.
     * 5. Enable http basic authentication to make the configuration complete.
     * You are free to use any other form of authentication.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
                .hasRole("ACTUATOR_ADMIN")
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * 访问所有的http端点都需要有ACTUATOR_ADMIN角色权限
     *
     * @param http
     * @return
     * @throws Exception
     */
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
                requests.anyRequest().hasRole("ACTUATOR_ADMIN"));
        http.httpBasic();
        return http.build();
    }*/

}
