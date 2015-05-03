package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 03.05.2015.
 */
public class ReceiversCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List receivers = DAOFactory.getFactory().getReceiverDAO().read();
        request.setAttribute("list", receivers);
        return PageManager.RECEIVER_PAGE;
    }
}
