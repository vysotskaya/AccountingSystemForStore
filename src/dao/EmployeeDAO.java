package dao;

import entity.Employee;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.04.2015.
 */
public class EmployeeDAO implements BaseDAO <Employee> {

    @Override
    public boolean create(Employee employee) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List read() throws HibernateException {
        Session session = null;
        List<Employee> employees = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            employees = session.createCriteria(Employee.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public boolean update(Employee employee) throws HibernateException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteById(int id) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.getNamedQuery("deleteEmployeeById").setParameter("employee_id", id).executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Employee getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Employee employee = (Employee)session.getNamedQuery("getEmployeeById")
                    .setParameter("employee_id", id).uniqueResult();
            return employee;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Employee getEmployeeByLogin(String employee_login) throws HibernateException {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Employee employee = (Employee)session.getNamedQuery("getEmployeeByLogin")
                    .setParameter("employee_login", employee_login).uniqueResult();
            return employee;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Employee> findEmployeeByName(String employee_name) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<Employee> employees = session.getNamedQuery("findEmployeeByName")
                    .setParameter("employee_name", employee_name).list();
            return employees;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
