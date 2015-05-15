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
    public boolean create(CustomsRegimeType regime) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(regime);
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
        List<CustomsRegimeType> regimes = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            regimes = session.createCriteria(CustomsRegimeType.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return regimes;
    }

    @Override
    public boolean update(CustomsRegimeType regime) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(regime);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
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
            session.getNamedQuery("deleteRegimeById").setParameter("regime_id", id).executeUpdate();
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
    public CustomsRegimeType getById (int id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            CustomsRegimeType regime = (CustomsRegimeType)session.getNamedQuery("getRegimeById")
                    .setParameter("regime_id", id).uniqueResult();
            return regime;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public CustomsRegimeType getRegimeByName (String name) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            CustomsRegimeType regime = (CustomsRegimeType)session.getNamedQuery("getRegimeByName")
                    .setParameter("regime_name", name).uniqueResult();
            return regime;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }
}
