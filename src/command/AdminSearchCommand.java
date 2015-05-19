package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by User on 19.05.2015.
 */
public class AdminSearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(AdminSearchCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == 2) {
                try {
                    String findStr = (String) request.getParameter("searchOption");
                    if (findStr.equals("") || findStr.replace(" ", "").equals("")) {
                        return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
                    } else {
                        Set<Employee> employeeSet = SearchService.getEmployees(findStr);
                        if (employeeSet == null) {
                            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
                        } else {
                            int size = employeeSet.size();
                            employeeSet.add(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin("admin"));
                            if (employeeSet.size() == size) {
                                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
                            }
                            request.setAttribute("list", employeeSet);
                            return PageManager.ADMIN_PAGE;
                        }
                    }
                } catch (HibernateException e) {
                    logger.error("hibernate error" ,e);
                } catch (NullPointerException ex) {
                    logger.error("incorrect parameter : ", ex);
                }
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            } else {
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            }
        }
    }
}
