package dao;

import entity.Receiver;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.04.2015.
 */
public class ReceiverDAO implements BaseDAO <Receiver> {

    @Override
    public boolean create(Receiver receiver) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(receiver);
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
        List<Receiver> receivers = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            receivers = session.createCriteria(Receiver.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return receivers;
    }

    @Override
    public boolean update(Receiver receiver) throws HibernateException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(receiver);
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
            session.getNamedQuery("deleteReceiverById").setParameter("receiver_id", id).executeUpdate();
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
    public Receiver getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Receiver receiver = (Receiver)session.getNamedQuery("getReceiverById")
                    .setParameter("receiver_id", id).uniqueResult();
            return receiver;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Receiver getReceiverByLegalAddress (String address) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Receiver receiver = (Receiver)session.getNamedQuery("getReceiverByLegalAddress")
                    .setParameter("legal_address", address).uniqueResult();
            return receiver;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
