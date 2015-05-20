package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
import dao.DAOFactory;
import entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 05.05.2015.
 */
public class EmployeesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if (role == DataConst.ADMIN_ID) {
                try {
                    List<Employee> employees = DAOFactory.getFactory().getEmployeeDAO().read();
                    request.setAttribute(RequestParam.RESULT_LIST, employees);
                    return PageManager.ADMIN_PAGE;
                } catch (HibernateException e) {
                    Logger logger = Logger.getLogger(EmployeesCommand.class);
                    logger.error("hibernate error" ,e);
                }
                return PageManager.LOGIN_PAGE;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
