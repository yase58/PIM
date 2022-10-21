package tr.com.yavuzduran.pim.authorization.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tr.com.yavuzduran.pim.authorization.common.CommonConstant;

public class HttpConfigurer extends AbstractHttpConfigurer<HttpConfigurer, HttpSecurity> {

    public HttpConfigurer(HttpSecurity security) throws Exception {
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.cors().and().csrf().disable();
        security.authorizeRequests((auth) -> {
            auth.requestMatchers(e -> e.getRequestURL().toString().equals(CommonConstant.loginAddress)).permitAll();
            auth.anyRequest().authenticated();
        });
        security.formLogin()
                .loginPage(CommonConstant.loginAddress)
                .loginProcessingUrl(CommonConstant.loginAddress);
    }

    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
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
