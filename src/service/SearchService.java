package service;

import dao.DAOFactory;
import entity.Product;
import entity.Record;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 14.05.2015.
 */
public class SearchService {
    public static Set<Record> getRecords(String findStr) {
        Set<Product> productSet = new HashSet();
        Set<Record> recordSet = new HashSet();

        List<Product> productMarkingList = DAOFactory.getFactory().getProductDAO().findProductByMarking(findStr);
        List<Product> productNameList = DAOFactory.getFactory().getProductDAO().findProductByName(findStr);
        List<Product> productAcountList = null;
        try {
            productAcountList = DAOFactory.getFactory().getProductDAO().findProductByAcount(Integer.parseInt(findStr));
        } catch (NumberFormatException ex) {}

        List<Product> productUnitList = DAOFactory.getFactory().getProductDAO().findProductByUnit(findStr);

        if (!productMarkingList.isEmpty()) {
            for (Product p : productMarkingList) {
                productSet.add(p);
            }
        }

        if (!productNameList.isEmpty()) {
            for (Product p : productNameList) {
                productSet.add(p);
            }
        }

        if (!productUnitList.isEmpty()) {
            for (Product p : productUnitList) {
                productSet.add(p);
            }
        }

        if (productAcountList != null) {
            for (Product p : productAcountList) {
                productSet.add(p);
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
}
