package command;

import configuration.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 03.05.2015.
 */
public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        if (login != null) {
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        }
        return PageManager.LOGIN_PAGE;
    }
}
