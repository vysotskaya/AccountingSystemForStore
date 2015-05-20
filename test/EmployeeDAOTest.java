import dao.DAOFactory;
import entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 21.04.2015.
 */
public class EmployeeDAOTest {
    private Employee employee;

//    @Before
//    public void setEmployee() {
//        DAOFactory.getFactory().getEmployeeDAO().create(new Employee("ivanov@gmail.com", "Иванов И.И.", "Ivanov",
//                "1234", DAOFactory.getFactory().getPositionDAO().getById(1)));
//        DAOFactory.getFactory().getEmployeeDAO().create(new Employee("petrov@mail.ru", "Петров П.С.", "Petrov",
//                "qwerty", DAOFactory.getFactory().getPositionDAO().getById(1)));
//        DAOFactory.getFactory().getEmployeeDAO().create(new Employee("qwerty", "qwerty", "qwerty",
//                "qwerty", DAOFactory.getFactory().getPositionDAO().getById(1)));
//
//    }
//

    @Ignore
    @Test
    public void getEmployeeByIdTest() {
        employee = new Employee("ivanov@gmail.com", "Иванов И.И.", "Ivanov",
                "1234", DAOFactory.getFactory().getPositionDAO().getById(1));
        employee.setEmployee_id(1);
        assertTrue(employee.equals(DAOFactory.getFactory().getEmployeeDAO().getById(1)));
    }

    @Ignore
    @Test
    public void updateEmployeeTest() {
        employee = new Employee("ivanov@gmail.com", "Иванов И.И.", "Ivanov",
                "123456", DAOFactory.getFactory().getPositionDAO().getById(1));
        employee.setEmployee_id(1);
        DAOFactory.getFactory().getEmployeeDAO().update(employee);
        assertTrue(employee.equals(DAOFactory.getFactory().getEmployeeDAO().getById(1)));
    }

    @Ignore
    @Test
    public void deleteEmployeeByIdTest() {
        DAOFactory.getFactory().getEmployeeDAO().deleteById(3);
        employee = new Employee();
        assertFalse(employee.equals(DAOFactory.getFactory().getEmployeeDAO().getById(3)));
    }

    @Ignore
    @Test
    public void getEmployeeByLoginTest() {
        employee = new Employee("ivanov@gmail.com", "Иванов И.И.", "Ivanov",
                "123456", DAOFactory.getFactory().getPositionDAO().getById(1));
        employee.setEmployee_id(1);
        assertTrue(employee.equals(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin("Ivanov")));
    }

    @Ignore
    @Test
    public void readEmployeeTest() {
        List<Employee> employees = DAOFactory.getFactory().getEmployeeDAO().read();
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
        assertTrue(true);
    }
}