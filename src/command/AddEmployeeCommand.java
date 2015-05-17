package command;

import configuration.PageManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.CreationListsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 05.05.2015.
 */
public class AddEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == 2) {
                try {
                    CreationListsService.createPositionList(request);
                    return PageManager.ADD_EMPLOYEE_PAGE;
                } catch (HibernateException e) {
                    Logger logger = Logger.getLogger(AddEmployeeCommand.class);
                    logger.error("hibernate error" ,e);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;

            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
