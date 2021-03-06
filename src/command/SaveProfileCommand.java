package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
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
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == DataConst.ADMIN_ID) {
                try {
                    int employee_id = Integer.parseInt((String)request.getParameter(RequestParam.EMPLOYEE_ID));
                    String name = (String) request.getParameter(RequestParam.EMPLOYEE_SURNAME_INPUT);
                    String email = (String) request.getParameter(RequestParam.EMPLOYEE_EMAIL_INPUT);
                    int position_id = Integer.parseInt((String) request
                            .getParameter(RequestParam.EMPLOYEE_POSITION_SELECT));

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
