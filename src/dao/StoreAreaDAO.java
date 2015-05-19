package dao;

import entity.StoreArea;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.04.2015.
 */
public class StoreAreaDAO implements BaseDAO<StoreArea> {

    @Override
    public boolean create(StoreArea storeArea) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(storeArea);
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
        List<StoreArea> areas = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            areas = session.createCriteria(StoreArea.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return areas;
    }

    @Override
    public boolean update(StoreArea storeArea) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(storeArea);
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
    public boolean deleteById(int id) throws HibernateException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.getNamedQuery("deleteStoreAreaById").setParameter("storearea_id", id).executeUpdate();
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
    public StoreArea getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            StoreArea storeArea = (StoreArea)session.getNamedQuery("getStoreAreaById")
                    .setParameter("storearea_id", id).uniqueResult();
            return storeArea;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<StoreArea> findStoreAreaByName(String findStr) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<StoreArea> storeAreas = session.getNamedQuery("findStoreAreaByName")
                    .setParameter("storearea_name", findStr).list();
            return storeAreas;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}