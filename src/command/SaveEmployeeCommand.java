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
import service.CreationListsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 05.05.2015.
 */
public class SaveEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SaveEmployeeCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == DataConst.ADMIN_ID) {
                try {
                    String name = (String) request.getParameter(RequestParam.EMPLOYEE_SURNAME_INPUT);
                    String email = (String) request.getParameter(RequestParam.EMPLOYEE_EMAIL_INPUT);
                    int position_id = Integer.parseInt((String) request
                            .getParameter(RequestParam.EMPLOYEE_POSITION_SELECT));
                    String login = (String) request.getParameter(RequestParam.LOGIN_INPUT);
                    String password = (String) request.getParameter(RequestParam.PASSWORD_INPUT);

                    if (CheckService.isNullParam(name, email, password, login)) {
                        throw new NullPointerException();
                    }

                    Employee employee = new Employee(email, name, login, password,
                            DAOFactory.getFactory().getPositionDAO().getById(position_id));

                    if (DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(login) == null) {
                        DAOFactory.getFactory().getEmployeeDAO().create(employee);
                    } else {
                        CreationListsService.createPositionList(request);
                        request.setAttribute(RequestParam.INCORRECT_DATA, true);
                        request.setAttribute(RequestParam.EMPLOYEE, employee);
                        return PageManager.ADD_EMPLOYEE_COMMAND;
                    }
                } catch (NullPointerException ex) {
                    logger.error("incorrect data in saving employee", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of position_id", ex);
                } catch (HibernateException ex) {
                    logger.error("hibernate error", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
