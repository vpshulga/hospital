package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.command.enums.CommandType;
import web.handlers.RequestHandler;

@WebServlet(urlPatterns = "/frontController")
public class FrontController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandType commandType = RequestHandler.getCommand(req);
        commandType.getController().execute(req, resp);
    }
}
