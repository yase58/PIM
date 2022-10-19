package tr.com.yavuzduran.pim.authorization.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tr.com.yavuzduran.pim.authorization.common.CommonConstant;
import tr.com.yavuzduran.pim.authorization.exception.JWTException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURL().toString().equals(CommonConstant.loginAddress)) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith(CommonConstant.jwtPrefix)) {
                try {
                    String token = authHeader.split(CommonConstant.jwtPrefix)[1];
                    Algorithm algorithm = Algorithm.HMAC512(CommonConstant.secret);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();
                    List<SimpleGrantedAuthority> authorities =
                            Stream.of(decodedJWT.getClaim(CommonConstant.jwtRole).asArray(String.class)).map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    throw new JWTException(e);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }


}
