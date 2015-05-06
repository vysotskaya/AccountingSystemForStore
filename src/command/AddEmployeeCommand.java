package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 05.05.2015.
 */
public class AddEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List positions = DAOFactory.getFactory().getPositionDAO().read();
        request.setAttribute("list", positions);
        return PageManager.ADD_EMPLOYEE_PAGE;
    }
}
