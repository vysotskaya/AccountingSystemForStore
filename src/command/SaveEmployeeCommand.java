package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 05.05.2015.
 */
public class SaveEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = (String) request.getParameter("surnameInput");
        String email = (String) request.getParameter("emailInput");
        int position_id = Integer.parseInt((String) request.getParameter("positionSelect"));
        String login = (String) request.getParameter("loginInput");
        String password = (String) request.getParameter("passwordInput");

        if (DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(login) == null) {
            DAOFactory.getFactory().getEmployeeDAO().create(new Employee(email, name, login, password,
                    DAOFactory.getFactory().getPositionDAO().getById(position_id)));
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        } else {
            request.setAttribute("isWrong", true);
            return PageManager.ADD_EMPLOYEE_COMMAND;
        }
    }
}
