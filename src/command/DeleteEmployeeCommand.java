package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by User on 05.05.2015.
 */
public class DeleteEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String)request.getParameter("employee_id"));
        DAOFactory.getFactory().getEmployeeDAO().deleteById(id);
        return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
    }
}
