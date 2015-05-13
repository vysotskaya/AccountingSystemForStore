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
        commandMap.put("showallproducts", new ProductsCommand());
        commandMap.put("showforbiddenproducts", new ForbiddenProductsCommand());
        commandMap.put("showstoringfeatures", new StoringFeaturesCommand());
        commandMap.put("showproductstodetention", new DetentionCommand());
        commandMap.put("signin", new SignInCommand());
        commandMap.put("showallrecords", new RecordsCommand());
        commandMap.put("editprofile", new EditProfileCommand());
        commandMap.put("editproduct", new EditProductCommand());
        commandMap.put("saveprofile", new SaveProfileCommand());
        commandMap.put("showallemployees", new EmployeesCommand());
        commandMap.put("saveemployee", new SaveEmployeeCommand());
        commandMap.put("addemployee", new AddEmployeeCommand());
        commandMap.put("deleteemployee", new DeleteEmployeeCommand());
        commandMap.put("saveproduct", new SaveProductCommand());
        commandMap.put("saveproductafteredit", new SaveProductAfterEditCommand());
        commandMap.put("addproduct", new AddProductCommand());
        commandMap.put("deleterecord", new DeleteRecordCommand());
        commandMap.put("generatereport", new GenerateReportCommand());
    }

    public static Command getCurrentCommand(String commandKey) {
        return (Command) commandMap.get((String) commandKey);
    }
}
