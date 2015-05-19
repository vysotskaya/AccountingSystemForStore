package command.manage;

import command.*;
import configuration.CommandConst;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 03.05.2015.
 */
public class CommandManager {
    private static Map commandMap = new HashMap<String, Command>();

    static {
        commandMap.put(CommandConst.AUTHORIZATION, new AuthorizationCommand());
        commandMap.put(CommandConst.LOGIN, new LoginCommand());
        commandMap.put(CommandConst.SHOW_ALL_SENDERS, new SendersCommand());
        commandMap.put(CommandConst.SHOW_ALL_RECEIVERS, new ReceiversCommand());
        commandMap.put(CommandConst.SHOW_ALL_PRODUCTS, new ProductsCommand());
        commandMap.put(CommandConst.SHOW_FORBIDDEN_PRODUCTS, new ForbiddenProductsCommand());
        commandMap.put(CommandConst.SHOW_STORING_FEATURES, new StoringFeaturesCommand());
        commandMap.put(CommandConst.SHOW_PRODUCTS_TO_DETENTION, new DetentionCommand());
        commandMap.put(CommandConst.SIGN_IN, new SignInCommand());
        commandMap.put(CommandConst.SHOW_ALL_RECORDS, new RecordsCommand());
        commandMap.put(CommandConst.EDIT_EMPLOYEE, new EditProfileCommand());
        commandMap.put(CommandConst.EDIT_PRODUCT, new EditProductCommand());
        commandMap.put(CommandConst.SAVE_EMPLOYEE_AFTER_EDIT, new SaveProfileCommand());
        commandMap.put(CommandConst.SHOW_ALL_EMPLOYEES, new EmployeesCommand());
        commandMap.put(CommandConst.SAVE_EMPLOYEE, new SaveEmployeeCommand());
        commandMap.put(CommandConst.ADD_EMPLOYEE, new AddEmployeeCommand());
        commandMap.put(CommandConst.DELETE_EMPLOYEE, new DeleteEmployeeCommand());
        commandMap.put(CommandConst.SAVE_PRODUCT, new SaveProductCommand());
        commandMap.put(CommandConst.SAVE_PRODUCT_AFTER_EDIT, new SaveProductAfterEditCommand());
        commandMap.put(CommandConst.ADD_PRODUCT, new AddProductCommand());
        commandMap.put(CommandConst.DELETE_PRODUCT, new DeleteRecordCommand());
        commandMap.put(CommandConst.GENERATE_REPORT, new GenerateReportCommand());
        commandMap.put(CommandConst.ADMIN_SEARCH, new AdminSearchCommand());
        commandMap.put(CommandConst.ENTER_PERIOD_FOR_REPORT, new EnterPeriodCommand());
        commandMap.put(CommandConst.SELECT_NEW_EMPLOYEE, new SelectEmployeeCommand());
        commandMap.put(CommandConst.SEARCH, new SearchCommand());
    }

    public static Command getCurrentCommand(String commandKey) {
        return (Command) commandMap.get((String) commandKey);
    }
}
