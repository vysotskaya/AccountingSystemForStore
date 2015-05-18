package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;
import entity.Record;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

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
        Logger logger = Logger.getLogger(DeleteEmployeeCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == 2) {
                try {
                    int id = Integer.parseInt((String) request.getParameter("employee_id"));
                    List<Record> recordList = DAOFactory.getFactory().getRecordDAO()
                            .findRecordsByEmployee(DAOFactory.getFactory().getEmployeeDAO().getById(id).getEmployee_name());
                    if (!recordList.isEmpty()) {
                        List<Employee> employeeList = DAOFactory.getFactory().getEmployeeDAO().read();
                        request.setAttribute("list", employeeList);
                        request.setAttribute("employee_id", id);
                        return PageManager.EMPLOYEE_SELECTION_PAGE;
                    } else {
                        DAOFactory.getFactory().getEmployeeDAO().deleteById(id);
                    }
                } catch (HibernateException e) {
                    logger.error("hibernate error" ,e);
                } catch (NullPointerException ex) {
                    logger.error("remove nonexistent employee : ", ex);
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
