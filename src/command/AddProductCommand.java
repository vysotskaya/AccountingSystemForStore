package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.SessionAttribute;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.CreationListsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 11.05.2015.
 */
public class AddProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role != DataConst.ADMIN_ID) {
                try {
                    CreationListsService.createRegimesAndAreasLists(request);
                    return PageManager.ADD_PRODUCT_PAGE;
                } catch (HibernateException e) {
                    Logger logger = Logger.getLogger(AddProductCommand.class);
                    logger.error("hibernate error" ,e);
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
