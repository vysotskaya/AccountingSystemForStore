package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;
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
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");

        if (role == null) {
            String login = (String) request.getParameter("loginInput");
            String password = (String) request.getParameter("passwordInput");

            if (login == null || password == null) {
                return PageManager.LOGIN_PAGE;
            }

            Employee employee = AuthorizationService.checkAuthorize(login, password);

            if (employee != null) {
                session = request.getSession();
                session.setAttribute("login", login);
                session.setAttribute("isAuthorized", true);
                session.setAttribute("role", employee.getPosition().getPosition_id());

                if (employee.getPosition().getPosition_id() != 2) {
                    return PageManager.SHOW_ALL_RECORDS_COMMAND;
                } else {
                    return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
                }
            } else {
                request.setAttribute("isWrong", true);
                return PageManager.LOGIN_PAGE;
            }
        } else if (role == 2) {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        } else {
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        }
    }
}
