package web.filters;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import web.servlet.FrontController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static web.command.Controller.MAIN_PAGE;

public class ExceptionsFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ExceptionsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {

            req.setAttribute("errorMsg", "error " + e.getMessage());


            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            try {
                req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
            } catch (ServletException | IOException e1) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    public void destroy() {

    }
}
