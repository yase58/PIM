package tr.com.yavuzduran.pim.security.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tr.com.yavuzduran.pim.security.common.CommonConstant;

public class HttpConfigurer extends AbstractHttpConfigurer<HttpConfigurer, HttpSecurity> {

    public HttpConfigurer(HttpSecurity security) throws Exception {
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.cors().and().csrf().disable();
        security.authorizeHttpRequests(auth -> {
            auth.requestMatchers(e -> e.getRequestURL().toString().equals(CommonConstant.LOGIN_ADDRESS)).permitAll();
            auth.anyRequest().authenticated();
        });
        security.formLogin()
                .loginPage(CommonConstant.LOGIN_ADDRESS)
                .loginProcessingUrl(CommonConstant.LOGIN_ADDRESS);
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        builder
                .addFilter(new JwtAuthenticationFilter(authenticationManager))
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);
    }

}
