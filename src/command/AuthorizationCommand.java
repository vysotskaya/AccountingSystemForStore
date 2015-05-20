package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
import entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.AuthorizationService;
import service.CheckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 03.05.2015.
 */
public class AuthorizationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute(SessionAttribute.ROLE);

        if (role == null) {
            try {
                String login = (String) request.getParameter(RequestParam.LOGIN_INPUT);
                String password = (String) request.getParameter(RequestParam.PASSWORD_INPUT);

                if (CheckService.isNullParam(login, password)) {
                    return PageManager.LOGIN_PAGE;
                }

                Employee employee = AuthorizationService.checkAuthorize(login, password);

                if (employee != null) {
                    session = request.getSession();
                    session.setAttribute(SessionAttribute.LOGIN, login);
                    session.setAttribute(SessionAttribute.AUTHORIZATION_PARAM, true);
                    session.setAttribute(SessionAttribute.ROLE, employee.getPosition().getPosition_id());

                    if (employee.getPosition().getPosition_id() != DataConst.ADMIN_ID) {
                        return PageManager.SHOW_ALL_RECORDS_COMMAND;
                    } else {
                        return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
                    }
                } else {
                    request.setAttribute(RequestParam.INCORRECT_DATA, true);
                    return PageManager.LOGIN_PAGE;
                }
            } catch (HibernateException e) {
                Logger logger = Logger.getLogger(AuthorizationCommand.class);
                logger.error("hibernate error" ,e);
            }
            return PageManager.LOGIN_PAGE;
        } else if (role == DataConst.ADMIN_ID) {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        } else {
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        }
    }
}
