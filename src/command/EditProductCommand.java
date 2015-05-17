package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Record;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.CreationListsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 13.05.2015.
 */
public class EditProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(EditProductCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if (role != 2) {
                try {
                    int id = Integer.parseInt((String) request.getParameter("record_id"));
                    Record record = DAOFactory.getFactory().getRecordDAO().getById(id);
                    if (record.getEmployee().getLogin().equals((String) session.getAttribute("login"))) {
                        CreationListsService.createRegimesAndAreasLists(request);
                        request.setAttribute("record", record);
                        return PageManager.EDIT_PRODUCT_PAGE;
                    } else {
                        request.setAttribute("isProhibited", true);
                        return PageManager.SHOW_ALL_RECORDS_COMMAND;
                    }
                } catch (HibernateException e){
                    logger.error("hibernate error" ,e);
                } catch (NullPointerException ex) {
                    logger.error("edit nonexistent record : ", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of record_id : ", ex);
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
