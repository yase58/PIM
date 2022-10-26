package tr.com.yavuzduran.pim.security.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * First Filter Request
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CyberSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = request.getHeader(headerNames.nextElement());
            if(checkCyberSecurity(header)){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    //TODO If a Security vulnerability is found. The Class to take action.
    private boolean checkCyberSecurity(String header) {
        return header.equals("security.vulnerable.finder");
    }

}
