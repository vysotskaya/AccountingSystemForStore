package command;

import configuration.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 03.05.2015.
 */
public class SignInCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session  = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return PageManager.LOGIN_PAGE;
    }
}
