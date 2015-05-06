package service;

import dao.DAOFactory;
import entity.Employee;

/**
 * Created by User on 03.05.2015.
 */
public class AuthorizationService {
//    private static String LOGIN = "olya";
//    private static String PASSWORD = "1234";

    public static Employee checkAuthorize(String login, String password) {
        Employee employee = DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(login);
        if ( employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }
}
