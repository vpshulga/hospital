package web.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.command.enums.CommandType;
import web.handlers.RequestHandler;

//@WebFilter(urlPatterns = {"/frontController"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        CommandType type = RequestHandler.getCommand(req);
        if (type.equals(CommandType.REGISTRATION) || type.equals(CommandType.DOCTOR)
                || type.equals(CommandType.CARD) || type.equals(CommandType.ADMIN)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            if (session.getAttribute("user") == null) {
                res.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
