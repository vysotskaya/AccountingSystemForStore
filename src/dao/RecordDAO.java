package dao;

import entity.*;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 21.04.2015.
 */
public class RecordDAO implements BaseDAO<Record> {

    @Override
    public boolean create(Record record) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(record);
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
    public List read() throws HibernateException{
        Session session = null;
        List<Record> records = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            records = session.createCriteria(Record.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return records;
        }

    }

    @Override
    public boolean update(Record record) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(record);
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
            session.getNamedQuery("deleteRecordById").setParameter("record_id", id).executeUpdate();
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
    public Record getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Record record = (Record)session.getNamedQuery("getRecordById")
                    .setParameter("record_id", id).uniqueResult();
            return record;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Record getByProductId (Product product) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Record record = (Record)session.getNamedQuery("getRecordByProductId")
                    .setParameter("product", product).uniqueResult();
            return record;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List  getRecordsForPeriod(String periodBegin, String periodEnd) throws HibernateException{
        String[] periodBeginStr = periodBegin.split("\\.");
        String[] periodEndStr = periodEnd.split("\\.");
        Date dateBegin = new Date(Integer.parseInt(periodBeginStr[2]), Integer.parseInt(periodBeginStr[1]),
                Integer.parseInt(periodBeginStr[0]));
        Date dateEnd = new Date(Integer.parseInt(periodEndStr[2]), Integer.parseInt(periodEndStr[1]),
                Integer.parseInt(periodEndStr[0]));
        try {
            List<Record> records = DAOFactory.getFactory().getRecordDAO().read();
            List<Record> recordList = new ArrayList();

            if (!records.isEmpty()) {
                for (Record r : records) {
                    String[] limit = r.getRetention_limit().split("\\.");
                    Date date = new Date(Integer.parseInt(limit[2]), Integer.parseInt(limit[1]),
                            Integer.parseInt(limit[0]));
                    if (date.compareTo(dateBegin) >= 0 && date.compareTo(dateEnd) <= 0) {
                        recordList.add(r);
                    }
                }
            }

            if (!recordList.isEmpty()) {
                return recordList;
            } else {
                return null;
            }
        } catch (HibernateException e) {
            throw e;
        }
    }

    public List<Record> getRecordsByProductRegime (String regime) throws HibernateException{
        Session session = null;
        try {
            List<Record> records = new ArrayList();
            List<Product> products = DAOFactory.getFactory().getProductDAO()
                .getProductsByRegime(DAOFactory.getFactory().getRegimeDAO().getRegimeByName(regime));

            session = HibernateUtil.openSession();
            if (!products.isEmpty()) {
                for (Product product : products) {
                    records.add(getByProductId(product));
                }
            }
            return records;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Record> getRecordsByEmployee (Employee employee) throws HibernateException{
        Session session = null;
        List<Record> records = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            records = session.getNamedQuery("getRecordsByEmployee").setParameter("employee", employee).list();
            return records;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Record> findRecordByLimit (String limit) throws HibernateException{
        Session session = null;
        List<Record> records = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            records = session.getNamedQuery("findRecordByLimit").setParameter("retention_limit", limit).list();
            return records;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Record> findRecordsByEmployee(String findStr) throws HibernateException{
        try {
            List<Employee> employees = DAOFactory.getFactory().getEmployeeDAO().findEmployeeByName(findStr);
            List<Record> records = new ArrayList();
            List<Record> resultList = new ArrayList();
            if (!employees.isEmpty()) {
                for (Employee employee : employees) {
                    records = getRecordsByEmployee(employee);
                    if (!records.isEmpty()) {
                        for (Record record1 : records) {
                            resultList.add(record1);
                        }
                    }
                }
            }

            if (!resultList.isEmpty()) {
                return resultList;
            } else {
                return new ArrayList();
            }
        } catch (HibernateException e) {
            throw e;
        }
    }

    public List<Record> findRecordsByStoreArea(String findStr) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<StoreArea> storeAreaList = DAOFactory.getFactory().getStoreAreaDAO().findStoreAreaByName(findStr);
            List<Record> records = new ArrayList();
            List<Record> resultList = new ArrayList();
            if (!storeAreaList.isEmpty()) {
                for (StoreArea storeArea : storeAreaList) {
                    records = session.getNamedQuery("getRecordsByStoreArea").setParameter("storeArea", storeArea).list();
                    if (!records.isEmpty()) {
                        for (Record record : records) {
                            resultList.add(record);
                        }
                    }
                }
            }

            if (!resultList.isEmpty()) {
                return resultList;
            } else {
                return new ArrayList();
            }
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Record> findRecordsByReceiver(String findStr) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<Receiver> receiverList = DAOFactory.getFactory().getReceiverDAO().findReceiverByName(findStr);
            List<Record> records = new ArrayList();
            List<Record> resultList = new ArrayList();
            if (!receiverList.isEmpty()) {
                for (Receiver receiver : receiverList) {
                    records = session.getNamedQuery("getRecordsByReceiver").setParameter("receiver", receiver).list();
                    if (!records.isEmpty()) {
                        for (Record record : records) {
                            resultList.add(record);
                        }
                    }
                }
            }

            if (!resultList.isEmpty()) {
                return resultList;
            } else {
                return new ArrayList();
            }
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Record> findRecordsBySender(String findStr) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<Sender> senders = DAOFactory.getFactory().getSenderDAO().findSenderByName(findStr);
            List<Record> records = new ArrayList();
            List<Record> resultList = new ArrayList();
            if (!senders.isEmpty()) {
                for (Sender sender : senders) {
                    records = session.getNamedQuery("getRecordsBySender").setParameter("sender", sender).list();
                    if (!records.isEmpty()) {
                        for (Record record : records) {
                            resultList.add(record);
                        }
                    }
                }
            }

            if (!resultList.isEmpty()) {
                return resultList;
            } else {
                return new ArrayList();
            }
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}