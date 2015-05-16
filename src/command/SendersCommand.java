package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 03.05.2015.
 */
public class SendersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute("role");
        if (role == null || role != 2) {
            List senders = DAOFactory.getFactory().getSenderDAO().read();
            request.setAttribute("list", senders);
            return PageManager.SENDER_PAGE;
        } else {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        }
    }
}
