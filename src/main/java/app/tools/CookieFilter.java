package app.tools;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CookieFilter implements Filter {


    private boolean isHttp(ServletRequest req){
        return req instanceof HttpServletRequest;
    }

    public boolean isLogged(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();

        if (cookies==null) return false;
        return Arrays.stream(cookies)
                .anyMatch(cookie -> cookie.getName().equals("login"));

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isHttp(servletRequest) && isLogged((HttpServletRequest) servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else ((HttpServletResponse) servletResponse).sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
