package command.manage;

import command.Command;
import command.EmptyCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 03.05.2015.
 */
public class CommandFactory {
    public static Command getCommand(HttpServletRequest request, HttpServletResponse response) {
        String action = (String) request.getParameter("command");

        Command command = CommandManager.getCurrentCommand(action);

        if (action == null || action.isEmpty()) {
            command = new EmptyCommand();
        }

        return command;
    }
}
