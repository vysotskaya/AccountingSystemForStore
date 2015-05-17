package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Record;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 03.05.2015.
 */
public class RecordsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");
        if (role == null || role != 2) {
            try {
                List<Record> records = DAOFactory.getFactory().getRecordDAO().read();
                request.setAttribute("list", records);
                return PageManager.STORAGE_PAGE;
            } catch (HibernateException e) {
                Logger logger = Logger.getLogger(RecordsCommand.class);
                logger.error("hibernate error" ,e);
            }
            return PageManager.LOGIN_PAGE;
        } else {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        }
    }
}
