package app.tools;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CookieFilter implements Filter {


    public boolean isHttp(ServletRequest req){
        return req instanceof HttpServletRequest;
    }

    public boolean isLogged(HttpServletRequest hreq){
        Cookie[] cookies = hreq.getCookies();

        if (cookies == null) return false;
        else{
            return Arrays.stream(cookies).anyMatch(c -> c.getName().equals("login"));
        }

    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isHttp(servletRequest ) && isLogged((HttpServletRequest) servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {( (HttpServletResponse)servletResponse).sendRedirect("/login");}
    }

    @Override
    public void destroy() {

    }
}
