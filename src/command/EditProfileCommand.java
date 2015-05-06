package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 04.05.2015.
 */
public class EditProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String)request.getParameter("emloyee_id"));
        Employee employee = DAOFactory.getFactory().getEmployeeDAO().getById(id);
        List positions = DAOFactory.getFactory().getPositionDAO().read();
        request.setAttribute("employee", employee);
        request.setAttribute("list", positions);
        return PageManager.EDIT_PROFILE_PAGE;
    }
}
