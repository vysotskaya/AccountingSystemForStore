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
    private Product product;

//    @Before
//    public void setProduct() {
//        DAOFactory.getFactory().getProductDAO().create(new Product(8, DAOFactory.getFactory().getRegimeDAO().getById(6),
//                "л.", "18J99", "отсутствуют", "Пиво"));
//        DAOFactory.getFactory().getProductDAO().create(new Product(2, DAOFactory.getFactory().getRegimeDAO().getById(4),
//                "шт.", "00TJ99", "отсутствуют", "Диван"));
//        DAOFactory.getFactory().getProductDAO().create(new Product(18, DAOFactory.getFactory().getRegimeDAO().getById(12),
//                "шт.", "0045AB9", "отсутствуют", "Пила"));
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
    public void getProductByMarkingTest() {
        Product product1 = DAOFactory.getFactory().getProductDAO().getById(3);
        assertTrue(product1.equals(DAOFactory.getFactory().getProductDAO()
                .getProductByMarking(product1.getProduct_marking())));
    }

    @Ignore
    @Test
    public void updateProductTest() {
        product = DAOFactory.getFactory().getProductDAO().getById(4);
        product.setProduct_marking("19B001");
        DAOFactory.getFactory().getProductDAO().update(product);
        assertTrue(product.equals(DAOFactory.getFactory().getProductDAO().getById(4)));
    }

    @Ignore
    @Test
    public void deleteProductByIdTest() {
        DAOFactory.getFactory().getProductDAO().deleteById(1);
        product = new Product();
        assertFalse(product.equals(DAOFactory.getFactory().getProductDAO().getById(1)));
    }

    @Ignore
    @Test
    public void readProductTest() {
        List<Product> products = DAOFactory.getFactory().getProductDAO().read();
        for (Product p : products) {
            System.out.println(p.toString());
        }
        assertTrue(products.size() == 4);
    }
}