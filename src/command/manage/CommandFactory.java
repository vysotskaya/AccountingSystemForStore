package command.manage;

import command.Command;
import command.EmptyCommand;
import configuration.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 03.05.2015.
 */
public class CommandFactory {
    public static Command getCommand (HttpServletRequest request, HttpServletResponse response) {
        String action = (String) request.getParameter(RequestParam.COMMAND);
        Command command = null;
        if (action == null || action.isEmpty()) {
            command = new EmptyCommand();
        } else {
            command = CommandManager.getCurrentCommand(action);
        }
        return command;
    }
}
