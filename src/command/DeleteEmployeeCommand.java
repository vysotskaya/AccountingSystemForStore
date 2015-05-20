package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
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
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == DataConst.ADMIN_ID) {
                try {
                    int id = Integer.parseInt((String) request.getParameter(RequestParam.EMPLOYEE_ID));
                    List<Record> recordList = DAOFactory.getFactory().getRecordDAO()
                            .findRecordsByEmployee(DAOFactory.getFactory().getEmployeeDAO().getById(id).getEmployee_name());
                    if (!recordList.isEmpty()) {
                        List<Employee> employeeList = DAOFactory.getFactory().getEmployeeDAO().read();
                        request.setAttribute(RequestParam.RESULT_LIST, employeeList);
                        request.setAttribute(RequestParam.EMPLOYEE_ID, id);
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
