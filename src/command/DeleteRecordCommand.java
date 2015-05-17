package command;

import configuration.PageManager;
import dao.DAOFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 11.05.2015.
 */
public class DeleteRecordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(DeleteRecordCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role != 2) {
                try {
                    int record_id = Integer.parseInt((String)request.getParameter("record_id"));
                    int product_id = DAOFactory.getFactory().getRecordDAO().getById(record_id).getProduct().getProduct_id();
                    DAOFactory.getFactory().getRecordDAO().deleteById(record_id);
                    DAOFactory.getFactory().getProductDAO().deleteById(product_id);
                } catch (HibernateException e) {
                    logger.error("hibernate error" ,e);
                } catch (NullPointerException ex) {
                    logger.error("remove nonexistent record : ", ex);
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
