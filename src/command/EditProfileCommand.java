package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.CreationListsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 04.05.2015.
 */
public class EditProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(EditProfileCommand.class);
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
                        CreationListsService.createPositionList(request);
                        request.setAttribute("employee", employee);
                        return PageManager.EDIT_PROFILE_PAGE;
                    }
                } catch (HibernateException e) {
                    logger.error("hibernate error : ", e);
                } catch (NullPointerException ex) {
                    logger.error("edit nonexistent employee : ", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of employee_id : ", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
