import dao.DAOFactory;
import entity.Employee;
import entity.Product;
import entity.Record;
import hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 21.04.2015.
 */
public class RecordDAOTest {
    private Record record;

//    @Before
//    public void setRecord() {
//        DAOFactory.getFactory().getRecordDAO().create(new Record(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin("kate"),
//                DAOFactory.getFactory().getProductDAO().getById(15), DAOFactory.getFactory().getReceiverDAO().getById(11),
//                "15.06.2015", DAOFactory.getFactory().getStoreAreaDAO().getById(1),
//                DAOFactory.getFactory().getSenderDAO().getById(12)));
//        DAOFactory.getFactory().getRecordDAO().create(new Record(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin("kate"),
//                DAOFactory.getFactory().getProductDAO().getById(16), DAOFactory.getFactory().getReceiverDAO().getById(15),
//                "28.05.2015", DAOFactory.getFactory().getStoreAreaDAO().getById(3),
//                DAOFactory.getFactory().getSenderDAO().getById(13)));
//        DAOFactory.getFactory().getRecordDAO().create(new Record(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin("olya"),
//                DAOFactory.getFactory().getProductDAO().getById(17), DAOFactory.getFactory().getReceiverDAO().getById(12),
//                "16.07.2015", DAOFactory.getFactory().getStoreAreaDAO().getById(2),
//                DAOFactory.getFactory().getSenderDAO().getById(11)));
//
//    }

    @Ignore
    @Test
    public void getRecordByIdTest() {
        record = new Record(DAOFactory.getFactory().getEmployeeDAO().getById(1),
                DAOFactory.getFactory().getProductDAO().getById(1), DAOFactory.getFactory().getReceiverDAO().getById(1),
                "15.06.2015", DAOFactory.getFactory().getStoreAreaDAO().getById(1),
                DAOFactory.getFactory().getSenderDAO().getById(1));
        record.setRecord_id(1);
        assertTrue(record.equals(DAOFactory.getFactory().getRecordDAO().getById(1)));
    }

    @Ignore
    @Test
    public void getRecordByProductIdTest() {
        record = DAOFactory.getFactory().getRecordDAO().getById(12);
        Product product = DAOFactory.getFactory().getProductDAO().getById(3);
        assertTrue(record.equals(DAOFactory.getFactory().getRecordDAO().getByProductId(product)));
    }

    @Ignore
    @Test
    public void updateRecordTest() {
        record = new Record(DAOFactory.getFactory().getEmployeeDAO().getById(1),
                DAOFactory.getFactory().getProductDAO().getById(1), DAOFactory.getFactory().getReceiverDAO().getById(1),
                "30.06.2015", DAOFactory.getFactory().getStoreAreaDAO().getById(1),
                DAOFactory.getFactory().getSenderDAO().getById(1));
        record.setRecord_id(1);
        DAOFactory.getFactory().getRecordDAO().update(record);
        assertTrue(record.equals(DAOFactory.getFactory().getRecordDAO().getById(1)));
    }

    @Ignore
    @Test
    public void deleteRecordByIdTest() {
        DAOFactory.getFactory().getRecordDAO().deleteById(3);
        record = new Record();
        assertFalse(record.equals(DAOFactory.getFactory().getRecordDAO().getById(3)));
    }

    @Ignore
    @Test
    public void readRecordTest() {
        List<Record> records = DAOFactory.getFactory().getRecordDAO().read();
        for (Record r : records) {
            System.out.println(r.toString());
        }
        assertTrue(records.size() == 4);
    }

    @Ignore
    @Test
    public void getRecordsForPeriodTest() {
        String periodBegin = "16.05.2015";
        String periodEnd = "16.08.2015";

        List recordList = DAOFactory.getFactory().getRecordDAO().getRecordsForPeriod(periodBegin, periodEnd);

        assertTrue(recordList.size() == 2);
    }

    @Ignore
    @Test
    public void findRecordByLimitTest() {
        String findStr = "05";
        List<Record> records = DAOFactory.getFactory().getRecordDAO().findRecordByLimit(findStr);
        for (Record r : records) {
            System.out.println(r.toString());
        }
        assertTrue(records.size() == 2);
    }

    @Ignore
    @Test
    public void findRecordByEmployeeTest() {
        String findStr = "Ом";
        List<Record> records = DAOFactory.getFactory().getRecordDAO().findRecordsByEmployee(findStr);
        assertTrue(records.size() == 1);
    }

    @Ignore
    @Test
    public void getRecordsByProductRegimeTest() {
        List<Record> records = DAOFactory.getFactory().getRecordDAO()
                .getRecordsByProductRegime("экспорт");
        for (Record r : records) {
            System.out.println(r.toString());
        }
        assertTrue(records.size() == 1);
    }
}