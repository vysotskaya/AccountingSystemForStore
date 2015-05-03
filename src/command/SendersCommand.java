package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 03.05.2015.
 */
public class SendersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List senders = DAOFactory.getFactory().getSenderDAO().read();
        request.setAttribute("list", senders);
        return PageManager.SENDER_PAGE;
    }
}
