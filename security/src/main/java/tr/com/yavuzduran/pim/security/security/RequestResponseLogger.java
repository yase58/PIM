package tr.com.yavuzduran.pim.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tr.com.yavuzduran.pim.security.common.CommonConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class RequestResponseLogger implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest requestToUse = (HttpServletRequest) servletRequest;
        HttpServletResponse responseToUse = (HttpServletResponse) servletResponse;
        requestLog(createRequestMessage(requestToUse));
        try {
            filterChain.doFilter(requestToUse, responseToUse);
        } finally {
            responseLog(createResponseMessage(requestToUse, responseToUse));
        }
    }

    private void requestLog(String message) {
        System.out.println(message);
    }

    private void responseLog(String message) {
        System.out.println(message);
    }

    private String createRequestMessage(HttpServletRequest request) {
        StringBuilder msg = new StringBuilder();
        msg.append("{[Request] : ");
        //METHOD POST, GET, DELETE
        msg.append('[').append(request.getMethod()).append(']');
        //URL
        msg.append('[').append(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append('?').append(queryString);
        }
        msg.append(']');
        //Client Info
        String client = request.getRemoteAddr();
        if (StringUtils.hasLength(client)) {
            msg.append('[').append("clientAddr=").append(client).append(']');
        }
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header != null && header.startsWith(CommonConstant.JWT_PREFIX)) {
                String token = header.split(CommonConstant.JWT_PREFIX)[1];
                Algorithm algorithm = Algorithm.HMAC512(CommonConstant.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                String username = decodedJWT.getSubject();
                if (StringUtils.hasLength(username)) {
                    msg.append('[').append("username=").append(username).append(']');
                }
            }
        } finally {
            msg.append('}');
        }
        return msg.toString();
    }

    private String createResponseMessage(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder msg = new StringBuilder();
        msg.append("{[Response Status] : ");
        //Response Status
        msg.append('[').append(response.getStatus()).append(']');
        //URL
        msg.append('[').append(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append('?').append(queryString);
        }
        msg.append(']');
        //Client Info
        String client = request.getRemoteAddr();
        if (StringUtils.hasLength(client)) {
            msg.append('[').append("clientAddr=").append(client).append(']');
        }
        return msg.toString();
    }

}
