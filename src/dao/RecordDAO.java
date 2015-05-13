package dao;

import entity.Product;
import entity.Record;
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
    public boolean create(Record record) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(record);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public List read() {
        Session session = null;
        List records = new ArrayList<Record>();
        try {
            session = HibernateUtil.openSession();
            records = session.createCriteria(Record.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return records;
    }

    @Override
    public boolean update(Record record) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(record);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return  false;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.getNamedQuery("deleteRecordById").setParameter("record_id", id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public Record getById (int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            Record record = (Record)session.getNamedQuery("getRecordById")
                    .setParameter("record_id", id).uniqueResult();
            transaction.commit();
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return new Record();
    }

    public Record getByProductId (Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            Record record = (Record)session.getNamedQuery("getRecordByProductId")
                    .setParameter("product", product).uniqueResult();
            transaction.commit();
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return new Record();
    }

    public List getRecordsForPeriod(String periodBegin, String periodEnd) {
        String[] periodBeginStr = periodBegin.split("\\.");
        String[] periodEndStr = periodEnd.split("\\.");
        Date dateBegin = new Date(Integer.parseInt(periodBeginStr[2]), Integer.parseInt(periodBeginStr[1]),
                Integer.parseInt(periodBeginStr[0]));
        Date dateEnd = new Date(Integer.parseInt(periodEndStr[2]), Integer.parseInt(periodEndStr[1]),
                Integer.parseInt(periodEndStr[0]));

        List records = DAOFactory.getFactory().getRecordDAO().read();
        List<Record> recordList = new ArrayList();

        Record record = null;
        for (Object o : records) {
            record = (Record) o;
            String[] limit = record.getRetention_limit().split("\\.");
            Date date = new Date(Integer.parseInt(limit[2]), Integer.parseInt(limit[1]),
                    Integer.parseInt(limit[0]));
            if (date.compareTo(dateBegin) >= 0 && date.compareTo(dateEnd) <= 0) {
                recordList.add(record);
            }
        }

        if (!recordList.isEmpty()) {
            return recordList;
        }

        return null;
    }

    public List<Record> getRecordsByProductRegime (String regime) {
        Session session = null;
        List<Record> records = new ArrayList();
        List<Product> products = DAOFactory.getFactory().getProductDAO()
                .getProductsByRegime(DAOFactory.getFactory().getRegimeDAO().getRegimeByName(regime));
        try {
            session = HibernateUtil.openSession();
            for (Product product : products) {
                records.add(getByProductId(product));
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }
}