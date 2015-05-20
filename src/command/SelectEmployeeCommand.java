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
 * Created by User on 18.05.2015.
 */
public class SelectEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SelectEmployeeCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role == DataConst.ADMIN_ID) {
                try {
                    int id = Integer.parseInt((String) request.getParameter(RequestParam.EMPLOYEE_TO_DELETE_ID));
                    int newEmployee_id = Integer.parseInt((String) request
                            .getParameter(RequestParam.NEW_RESPONSIBLE_EMPLOYEE));
                    List<Record> recordList = DAOFactory.getFactory().getRecordDAO()
                            .findRecordsByEmployee(DAOFactory.getFactory().getEmployeeDAO().getById(id).getEmployee_name());
                    if (!recordList.isEmpty()) {
                        Employee employee = DAOFactory.getFactory().getEmployeeDAO().getById(newEmployee_id);
                        if (employee != null) {
                            for (Record record : recordList) {
                                record.setEmployee(employee);
                                DAOFactory.getFactory().getRecordDAO().update(record);
                            }
                        }
                    }
                    DAOFactory.getFactory().getEmployeeDAO().deleteById(id);
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
