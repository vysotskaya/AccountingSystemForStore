package command.manage;

import command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 03.05.2015.
 */
public class CommandManager {
    private static Map commandMap = new HashMap<String, Command>();

    static {
        commandMap.put("authorization", new AuthorizationCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("showallsenders", new SendersCommand());
        commandMap.put("showallreceivers", new ReceiversCommand());
        commandMap.put("signin", new SignInCommand());
        commandMap.put("showallrecords", new RecordsCommand());
    }

    public static Command getCurrentCommand(String commandKey) {
        return (Command) commandMap.get((String) commandKey);
    }
}