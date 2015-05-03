package command;

import configuration.PageManager;
import dao.DAOFactory;
import service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 03.05.2015.
 */
public class AuthorizationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getParameter("loginInput");
        String password = (String) request.getParameter("passwordInput");

        if (AuthorizationService.checkAuthorize(login, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("isAuthorized", true);
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            request.setAttribute("isWrong", true);
            return PageManager.LOGIN_PAGE;
        }
    }
}