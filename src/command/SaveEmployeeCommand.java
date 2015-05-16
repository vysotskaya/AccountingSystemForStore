package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 05.05.2015.
 */
public class SaveEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = null;
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

                    if (name == null || email == null || password == null || login == null) {
                        throw new NullPointerException();
                    }

                    Employee employee = new Employee(email, name, login, password,
                            DAOFactory.getFactory().getPositionDAO().getById(position_id));

                    if (DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(login) == null) {
                        DAOFactory.getFactory().getEmployeeDAO().create(employee);
                    } else {
                        List positions = DAOFactory.getFactory().getPositionDAO().read();
                        request.setAttribute("list", positions);
                        request.setAttribute("isWrong", true);
                        request.setAttribute("employee", employee);
                        return PageManager.ADD_EMPLOYEE_COMMAND;
                    }
                } catch (NullPointerException ex) {
                    logger = Logger.getLogger(SaveEmployeeCommand.class);
                    logger.error("incorrect data in saving employee", ex);
                } catch (NumberFormatException ex) {
                    logger = Logger.getLogger(SaveEmployeeCommand.class);
                    logger.error("incorrect format of position_id", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
