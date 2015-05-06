package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 05.05.2015.
 */
public class EmployeesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List employees = DAOFactory.getFactory().getEmployeeDAO().read();
        request.setAttribute("list", employees);
        return PageManager.ADMIN_PAGE;
    }
}
