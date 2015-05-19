package dao;

import entity.Sender;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.04.2015.
 */
public class SenderDAO implements BaseDAO <Sender>{

    @Override
    public boolean create(Sender sender) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(sender);
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
        List<Sender> senders = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            senders = session.createCriteria(Sender.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return senders;
    }

    @Override
    public boolean update(Sender sender) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(sender);
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
            session.getNamedQuery("deleteSenderById").setParameter("sender_id", id).executeUpdate();
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
    public Sender getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Sender sender = (Sender)session.getNamedQuery("getSenderById")
                    .setParameter("sender_id", id).uniqueResult();
            return sender;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Sender getSenderByLegalAddress (String address) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Sender sender = (Sender)session.getNamedQuery("getSenderByLegalAddress")
                    .setParameter("legal_address", address).uniqueResult();
            return sender;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Sender> findSenderByNameAddressPhoneEmail (String findStr) throws HibernateException{
        Session session = null;
        List<Sender> senders = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            senders = session.getNamedQuery("findSenderByNameAddressPhoneEmail")
                    .setParameter("sender_name", findStr)
                    .setParameter("legal_address", findStr)
                    .setParameter("phone", findStr)
                    .setParameter("email", findStr).list();
            return senders;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Sender> findSenderByName (String findStr) throws HibernateException{
        Session session = null;
        List<Sender> senders = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            senders = session.getNamedQuery("findSenderByName")
                    .setParameter("sender_name", findStr).list();
            return senders;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
