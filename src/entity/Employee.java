package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by User on 21.04.2015.
 */

@Entity
@Table(name = ("employee"))
@NamedQueries({
        @NamedQuery(name = "deleteEmployeeById", query = "delete from entity.Employee e " +
                "where e.employee_id = :employee_id"),
        @NamedQuery(name = "getEmployeeById", query = "from entity.Employee e " +
                "where e.employee_id = :employee_id")})

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int employee_id;

    @Column(name = "employee_name")
    private String employee_name;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    private Position position;

    public Employee() {
    }

    public Employee(Employee employee) {
        this.employee_name = employee.getEmployee_name();
        this.email = employee.getEmail();
        this.login = employee.getLogin();
        this.password = employee.getPassword();
        this.position = employee.getPosition();
    }

    public Employee(String email, String employee_name, String login, String password, Position position) {
        this.email = email;
        this.employee_name = employee_name;
        this.login = login;
        this.password = password;
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    public Position getPosition() {
        return position;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee: id = " + employee_id + " email = " + email +  ", name = " + employee_name
                + ", login = " + login + ", password = " + password
                + ", position = " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (employee_id != employee.employee_id) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (employee_name != null ? !employee_name.equals(employee.employee_name) : employee.employee_name != null)
            return false;
        if (login != null ? !login.equals(employee.login) : employee.login != null) return false;
        if (password != null ? !password.equals(employee.password) : employee.password != null) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employee_id;
        result = 31 * result + (employee_name != null ? employee_name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}


