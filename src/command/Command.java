package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 03.05.2015.
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
