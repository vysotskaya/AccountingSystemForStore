package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.CheckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 04.05.2015.
 */
public class SaveProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SaveProfileCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == 2) {
                try {
                    int employee_id = Integer.parseInt((String)request.getParameter("employee_id"));
                    String name = (String) request.getParameter("surnameInput");
                    String email = (String) request.getParameter("email");
                    int position_id = Integer.parseInt((String) request.getParameter("positionSelect"));

                    if (CheckService.isNullParam(name, email)) {
                        throw new NullPointerException();
                    }

                    Employee employee = DAOFactory.getFactory().getEmployeeDAO().getById(employee_id);
                    employee.setPosition(DAOFactory.getFactory().getPositionDAO().getById(position_id));
                    employee.setEmail(email);
                    employee.setEmployee_name(name);

                    DAOFactory.getFactory().getEmployeeDAO().update(employee);

                } catch (HibernateException e) {
                    logger.error("hibernate error", e);
                } catch (NullPointerException ex) {
                    logger.error("incorrect data in saving employee", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of position_id or employee_id", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
