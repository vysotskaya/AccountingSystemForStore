package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
import entity.Record;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.DetentionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class DetentionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        if (role == null || role != DataConst.ADMIN_ID) {
            try {
                List<Record> records = DetentionService.getProductsToDetention();
                request.setAttribute(RequestParam.RESULT_LIST, records);
                return PageManager.PRODUCT_TO_DETENTION_PAGE;
            } catch (HibernateException e) {
                Logger logger = Logger.getLogger(DetentionCommand.class);
                logger.error("hibernate error" ,e);
            }
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        }
    }
}
