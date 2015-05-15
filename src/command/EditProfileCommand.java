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
 * Created by User on 04.05.2015.
 */
public class EditProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if (role == 2) {
                try {
                    int id = Integer.parseInt((String)request.getParameter("emloyee_id"));
                    Employee employee = DAOFactory.getFactory().getEmployeeDAO().getById(id);
                    if (employee != null) {
                        List positions = DAOFactory.getFactory().getPositionDAO().read();
                        request.setAttribute("employee", employee);
                        request.setAttribute("list", positions);
                        return PageManager.EDIT_PROFILE_PAGE;
                    }
                } catch (NullPointerException ex) {
                    Logger logger = Logger.getLogger(EditProfileCommand.class);
                    logger.error("edit nonexistent employee : ", ex);
                } catch (NumberFormatException ex) {
                    Logger logger = Logger.getLogger(EditProfileCommand.class);
                    logger.error("incorrect format of employee_id : ", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
