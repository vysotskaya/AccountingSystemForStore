package service;

import dao.DAOFactory;
import entity.*;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 14.05.2015.
 */
public class SearchService {
    public static Set<Record> getRecords(String findStr) throws HibernateException{
        Set<Record> recordSet = new HashSet();

        List<Product> productList = DAOFactory.getFactory().getProductDAO().findProductByNameAcountUnitMarking(findStr);

        if (!productList.isEmpty()) {
            for (Product p : productList) {
                recordSet.add(DAOFactory.getFactory().getRecordDAO().getByProductId(p));
            }
        }

        List<Record> recordLimitList = DAOFactory.getFactory().getRecordDAO().findRecordByLimit(findStr);

        if (!recordLimitList.isEmpty()) {
            for (Record r : recordLimitList) {
                recordSet.add(r);
            }
        }

        List<Record> recordEmployeeList = DAOFactory.getFactory().getRecordDAO().findRecordsByEmployee(findStr);

        if (!recordEmployeeList.isEmpty()) {
            for (Record r : recordEmployeeList) {
                recordSet.add(r);
            }
        }

        if (!recordSet.isEmpty()) {
            return recordSet;
        }
        return null;
    }

    public static Set<Employee> getEmployees(String findStr) throws HibernateException{
        Set<Employee> employeeSet = new HashSet();

        List<Employee> employeeNameEmailList = DAOFactory.getFactory().getEmployeeDAO().findEmployeeByNameEmail(findStr);
        List<Employee> employeePositionList = DAOFactory.getFactory().getEmployeeDAO().findEmployeeByPosition(findStr);

        if (!employeeNameEmailList.isEmpty()) {
            for (Employee e : employeeNameEmailList) {
                employeeSet.add(e);
            }
        }

        if (!employeePositionList.isEmpty()) {
            for (Employee e : employeePositionList) {
                employeeSet.add(e);
            }
        }

        if (!employeeSet.isEmpty()) {
            return employeeSet;
        }
        return null;
    }

    public static List<Receiver> getReceivers(String findStr) throws HibernateException{
        List<Receiver> receivers = DAOFactory.getFactory().getReceiverDAO().findReceiverByNameAddressPhoneEmail(findStr);
        if (!receivers.isEmpty()) {
            return receivers;
        }
        return null;
    }

    public static List<Sender> getSenders(String findStr) throws HibernateException{
        List<Sender> senders = DAOFactory.getFactory().getSenderDAO().findSenderByNameAddressPhoneEmail(findStr);
        if (!senders.isEmpty()) {
            return senders;
        }
        return null;
    }

    public static List<Product> getProductForFeatures(String findStr) throws HibernateException{
        List<Product> products = DAOFactory.getFactory().getProductDAO().findProductByNameMarkingFeatures(findStr);
        if (!products.isEmpty()) {
            return products;
        }
        return null;
    }

    public static Set<Record> getProducts(String findStr) throws HibernateException {
        Set<Record> recordSet = new HashSet();
        Set<Product> productSet = new HashSet();

        List<Product> productList = DAOFactory.getFactory().getProductDAO().findProductByNameMarking(findStr);
        if (!productList.isEmpty()) {
            for (Product p : productList) {
                productSet.add(p);
            }
            productList = null;
        }

        List<CustomsRegimeType> regimeTypeList = DAOFactory.getFactory().getRegimeDAO().findRegimeByName(findStr);
        if (!regimeTypeList.isEmpty()) {
            for (CustomsRegimeType regimeType : regimeTypeList) {
                productList = DAOFactory.getFactory().getProductDAO().getProductsByRegime(regimeType);
                if (!productList.isEmpty()) {
                    for (Product product : productList) {
                        productSet.add(product);
                    }
                    productList = null;
                }
            }

        }
        if (!productSet.isEmpty()) {
            for (Product p : productSet) {
                recordSet.add(DAOFactory.getFactory().getRecordDAO().getByProductId(p));
            }
        }

        List<Record> recordAreaList = DAOFactory.getFactory().getRecordDAO().findRecordsByStoreArea(findStr);

        if (!recordAreaList.isEmpty()) {
            for (Record r : recordAreaList) {
                recordSet.add(r);
            }
        }

        List<Record> recordList = DAOFactory.getFactory().getRecordDAO().findRecordsByReceiver(findStr);

        if (!recordList.isEmpty()) {
            for (Record r : recordList) {
                recordSet.add(r);
            }
        }

        recordList = DAOFactory.getFactory().getRecordDAO().findRecordsBySender(findStr);

        if (!recordList.isEmpty()) {
            for (Record r : recordList) {
                recordSet.add(r);
            }
        }

        if (!recordSet.isEmpty()) {
            return recordSet;
        }
        return null;
    }

    public static Set<Record> getForbiddenProducts(String findStr) throws HibernateException {
        Set<Record> recordSet = new HashSet();
        Set<Product> productSet = new HashSet();

        List<Product> productList = DAOFactory.getFactory().getProductDAO().findProductByNameAcountUnitMarking(findStr);
        if (!productList.isEmpty()) {
            for (Product p : productList) {
                productSet.add(p);
            }
            productList = null;
        }

        List<CustomsRegimeType> regimeTypeList = DAOFactory.getFactory().getRegimeDAO().findRegimeByName(findStr);
        if (!regimeTypeList.isEmpty()) {
            for (CustomsRegimeType regimeType : regimeTypeList) {
                productList = DAOFactory.getFactory().getProductDAO().getProductsByRegime(regimeType);
                if (!productList.isEmpty()) {
                    for (Product product : productList) {
                        productSet.add(product);
                    }
                    productList = null;
                }
            }

        }
        if (!productSet.isEmpty()) {
            for (Product p : productSet) {
                recordSet.add(DAOFactory.getFactory().getRecordDAO().getByProductId(p));
            }
        }

        List<Record> recordLimitList = DAOFactory.getFactory().getRecordDAO().findRecordByLimit(findStr);

        if (!recordLimitList.isEmpty()) {
            for (Record r : recordLimitList) {
                recordSet.add(r);
            }
        }


        if (!recordSet.isEmpty()) {
            return recordSet;
        }
        return null;
    }

    public static Set<Record> getProductsToDetention(String findStr) throws HibernateException {
        Set<Record> recordSet = new HashSet();

        List<Product> productList = DAOFactory.getFactory().getProductDAO().findProductByNameAcountUnitMarking(findStr);
        if (!productList.isEmpty()) {
            for (Product p : productList) {
                recordSet.add(DAOFactory.getFactory().getRecordDAO().getByProductId(p));
            }
        }

        List<Record> recordLimitList = DAOFactory.getFactory().getRecordDAO().findRecordByLimit(findStr);

        if (!recordLimitList.isEmpty()) {
            for (Record r : recordLimitList) {
                recordSet.add(r);
            }
        }

        if (!recordSet.isEmpty()) {
            return recordSet;
        }
        return null;
    }
}
