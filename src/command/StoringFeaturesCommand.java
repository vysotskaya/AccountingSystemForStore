package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
import dao.DAOFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class StoringFeaturesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute(SessionAttribute.ROLE);
        if (role == null || role != DataConst.ADMIN_ID) {
            try {
                List products = DAOFactory.getFactory().getProductDAO().read();
                request.setAttribute(RequestParam.RESULT_LIST, products);
                return PageManager.STORING_FEATURES_PAGE;
            } catch (HibernateException e) {
                Logger logger = Logger.getLogger(SendersCommand.class);
                logger.error("hibernate error", e);
            }
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        }
    }
}
