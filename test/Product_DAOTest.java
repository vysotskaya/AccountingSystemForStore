import dao.DAOFactory;
import entity.CustomsRegimeType;
import entity.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

public class Product_DAOTest {

    private CustomsRegimeType regime;
    private Product product;

//    @Before
//    public void setProduct() {
//        DAOFactory.getFactory().getProductDAO().create(new Product(8, DAOFactory.getFactory().getRegimeDAO().getById(2),
//                "шт.", "18J99", "нет", "пиво"));
//        DAOFactory.getFactory().getProductDAO().create(new Product(2, DAOFactory.getFactory().getRegimeDAO().getById(4),
//                "шт.", "18J99", "нет", "диван"));
//    }

//    @After
//    public void clearProduct() {
//        DAOFactory.getFactory().getProductDAO().deleteById(1);
//    }

    @Ignore
    @Test
    public void getProductByIdTest() {
        product.setProduct_id(1);
        assertTrue(product.equals(DAOFactory.getFactory().getProductDAO().getById(1)));
    }

    @Ignore
    @Test
    public void updateProductTest() {
        product.setAcount(9999);
        product.setProduct_id(1);
        DAOFactory.getFactory().getProductDAO().update(product);
        assertTrue(product.equals(DAOFactory.getFactory().getProductDAO().getById(1)));
    }

    @Ignore
    @Test
    public void deleteProductByIdTest() {
        DAOFactory.getFactory().getProductDAO().deleteById(1);
        product = new Product();
        assertFalse(product.equals(DAOFactory.getFactory().getProductDAO().getById(1)));
    }

//    @Ignore
    @Test
    public void readProductTest() {
        List products = DAOFactory.getFactory().getProductDAO().read();
        for (Object o : products) {
            System.out.println(o.toString());
        }
        assertTrue(products.size() == 2);
    }
}