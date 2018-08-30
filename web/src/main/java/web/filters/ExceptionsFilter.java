package web.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;

import static web.command.Controller.MAIN_PAGE;

public class ExceptionsFilter implements Filter {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ExceptionsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try {
            logger.info("test");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {

            req.setAttribute("errorMsg", "error " + e.getMessage());


            logger.error(ExceptionUtils.getStackTrace(e));
            try {
                req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
            } catch (ServletException | IOException e1) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    public void destroy() {

    }
}
