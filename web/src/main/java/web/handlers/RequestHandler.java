package web.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import web.command.enums.CommandType;

import static web.command.enums.CommandType.WORKERS;

public class RequestHandler {
    public static CommandType getCommand(HttpServletRequest req) {
        String param = req.getParameter("command");
        if (param == null || "".equals(param)) {
            param = "workers";
        }


        CommandType type = CommandType.getByPageName(param);
        req.setAttribute("title", type.getI18nKey());
        req.setAttribute("title", type.getPageName());
        HttpSession session = req.getSession();
        String pageName = (String) session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", WORKERS.getPageName());
            session.setAttribute("pagePath", WORKERS.getPagePath());
        }
        return type;
    }
}
