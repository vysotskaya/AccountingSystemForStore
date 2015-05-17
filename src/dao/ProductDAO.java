package dao;

import entity.CustomsRegimeType;
import entity.Product;
import hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.04.2015.
 */
public class ProductDAO implements BaseDAO <Product> {

    @Override
    public boolean create(Product product) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(product);
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
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            products = session.createCriteria(Product.class).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }

    @Override
    public boolean update(Product product) throws HibernateException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(product);
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
            session.getNamedQuery("deleteProductById").setParameter("product_id", id).executeUpdate();
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
    public Product getById (int id) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Product product = (Product)session.getNamedQuery("getProductById")
                    .setParameter("product_id", id).uniqueResult();
            return product;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Product getProductByMarking (String marking) throws HibernateException{
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Product product = (Product)session.getNamedQuery("getProductByMarking")
                    .setParameter("product_marking", marking).uniqueResult();
            return product;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Product> getProductsByRegime (CustomsRegimeType regimeType) throws HibernateException{
        Session session = null;
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            products = session.getNamedQuery("getProductsByRegime").setParameter("regime", regimeType).list();
            return products;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Product> findProductByMarking (String marking) throws HibernateException{
        Session session = null;
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            products = session.getNamedQuery("findProductByMarking").setParameter("product_marking", marking).list();
            return products;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Product> findProductByName (String name) throws HibernateException{
        Session session = null;
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            products = session.getNamedQuery("findProductByName").setParameter("product_name", name).list();
            return products;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Product> findProductByAcount (int acount) throws HibernateException{
        Session session = null;
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            products = session.getNamedQuery("findProductByAcount").setParameter("acount", acount).list();
            return products;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Product> findProductByUnit (String unit) throws HibernateException{
        Session session = null;
        List<Product> products = new ArrayList();
        try {
            session = HibernateUtil.openSession();
            products = session.getNamedQuery("findProductByUnit").setParameter("measuring_unit", unit).list();
            return products;
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
