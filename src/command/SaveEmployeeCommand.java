package command;

import configuration.PageManager;
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
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == 2) {
                try {
                    String name = (String) request.getParameter("surnameInput");
                    String email = (String) request.getParameter("emailInput");
                    int position_id = Integer.parseInt((String) request.getParameter("positionSelect"));
                    String login = (String) request.getParameter("loginInput");
                    String password = (String) request.getParameter("passwordInput");

                    if (CheckService.isNullParam(name, email, password, login)) {
                        throw new NullPointerException();
                    }

                    Employee employee = new Employee(email, name, login, password,
                            DAOFactory.getFactory().getPositionDAO().getById(position_id));

                    if (DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(login) == null) {
                        DAOFactory.getFactory().getEmployeeDAO().create(employee);
                    } else {
                        CreationListsService.createPositionList(request);
                        request.setAttribute("isWrong", true);
                        request.setAttribute("employee", employee);
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
