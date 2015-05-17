package service;

import dao.DAOFactory;
import entity.Employee;
import org.hibernate.HibernateException;

/**
 * Created by User on 03.05.2015.
 */
public class AuthorizationService {
    public static Employee checkAuthorize(String login, String password) throws HibernateException{
        Employee employee = DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(login);
        if ( employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }
}
