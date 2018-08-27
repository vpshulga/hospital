package web.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

//@WebFilter(urlPatterns = {"/*", }, initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null && !"".equals(encodingParam)) {
            encoding = encodingParam;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);

        servletResponse.setContentType("text/html; charset=" + encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
