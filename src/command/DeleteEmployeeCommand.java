package command;

import configuration.PageManager;
import dao.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by User on 05.05.2015.
 */
public class DeleteEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == 2) {
                try {
                    int id = Integer.parseInt((String) request.getParameter("employee_id"));
                    DAOFactory.getFactory().getEmployeeDAO().deleteById(id);
                } catch (NullPointerException ex) {
                    Logger logger = Logger.getLogger(DeleteEmployeeCommand.class);
                    logger.error("remove nonexistent employee : ", ex);
                } catch (NumberFormatException ex) {
                    Logger logger = Logger.getLogger(DeleteEmployeeCommand.class);
                    logger.error("incorrect format of employee_id : ", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
