package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 04.05.2015.
 */
public class SaveProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int employee_id = Integer.parseInt((String)request.getParameter("employee_id"));
        String name = (String) request.getParameter("surnameInput");
        String email = (String) request.getParameter("email");
        int position_id = Integer.parseInt((String) request.getParameter("positionSelect"));

        Employee employee = DAOFactory.getFactory().getEmployeeDAO().getById(employee_id);
        employee.setPosition(DAOFactory.getFactory().getPositionDAO().getById(position_id));
        employee.setEmail(email);
        employee.setEmployee_name(name);

        DAOFactory.getFactory().getEmployeeDAO().update(employee);

        return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
    }
}
