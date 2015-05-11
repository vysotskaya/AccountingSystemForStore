package dao;

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
    public boolean create(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.save(product);
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
        List products = new ArrayList<Product>();
        try {
            session = HibernateUtil.openSession();
            products = session.createCriteria(Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }

    @Override
    public boolean update(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.update(product);
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
            session.getNamedQuery("deleteProductById").setParameter("product_id", id).executeUpdate();
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
    public Product getById (int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            Product product = (Product)session.getNamedQuery("getProductById")
                    .setParameter("product_id", id).uniqueResult();
            transaction.commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return new Product();
    }

    public Product getProductByMarking (String marking) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Product product = (Product)session.getNamedQuery("getProductByMarking")
                    .setParameter("product_marking", marking).uniqueResult();
            return product;
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
