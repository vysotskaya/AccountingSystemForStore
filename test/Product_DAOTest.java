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

    @Before
    public void setProduct() {
        regime  = new CustomsRegimeType("свободное пользование");
        product = new Product(8, DAOFactory.getFactory().getRegimeDAO().getById(1),
                "шт.", "18J99", "нет", "пиво");
        DAOFactory.getFactory().getProductDAO().create(product);
    }

    @After
    public void clearProduct() {
        DAOFactory.getFactory().getProductDAO().deleteById(1);
    }

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
        DAOFactory.getFactory().getProductDAO().update(product);
        product.setProduct_id(1);
        assertTrue(product.equals(DAOFactory.getFactory().getProductDAO().getById(1)));
    }

//    @Ignore
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
        assertTrue(products.size() == 1);
    }
}