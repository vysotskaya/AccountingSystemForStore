package dao;

import entity.CustomsRegimeType;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.04.2015.
 */
public class RegimeDAO implements BaseDAO<CustomsRegimeType> {

    @Override
    public boolean create(CustomsRegimeType regime) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(regime);
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
        List<CustomsRegimeType> regimes = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            regimes = session.createCriteria(CustomsRegimeType.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return regimes;
    }

    @Override
    public boolean update(CustomsRegimeType regime) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(regime);
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
            session.getNamedQuery("deleteRegimeById").setParameter("regime_id", id).executeUpdate();
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
    public CustomsRegimeType getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            CustomsRegimeType regime = (CustomsRegimeType)session.getNamedQuery("getRegimeById")
                    .setParameter("regime_id", id).uniqueResult();
            return regime;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public CustomsRegimeType getRegimeByName (String name) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            CustomsRegimeType regime = (CustomsRegimeType)session.getNamedQuery("getRegimeByName")
                    .setParameter("regime_name", name).uniqueResult();
            return regime;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
