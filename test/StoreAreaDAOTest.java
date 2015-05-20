import dao.DAOFactory;
import entity.StoreArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 21.04.2015.
 */
public class StoreAreaDAOTest {
    private StoreArea storeArea;

//    @Before
//    public void setStoreArea() {
//        DAOFactory.getFactory().getStoreAreaDAO().create(new StoreArea("открытая площадь"));
//        DAOFactory.getFactory().getStoreAreaDAO().create(new StoreArea("зона А"));
//        DAOFactory.getFactory().getStoreAreaDAO().create(new StoreArea("зона В"));
//        DAOFactory.getFactory().getStoreAreaDAO().create(new StoreArea("qwerty"));
//    }

//    @After
//    public void cleaStoreArea() {
//        DAOFactory.getFactory().getStoreAreaDAO().deleteById(1);)
//    }

    @Ignore
    @Test
    public void getStoreAreaByIdTest() {
        storeArea = new StoreArea("зона А");
        storeArea.setStorearea_id(2);
        assertTrue(storeArea.equals(DAOFactory.getFactory().getStoreAreaDAO().getById(2)));
    }

    @Ignore
    @Test
    public void updateStoreAreaTest() {
        storeArea = new StoreArea("зона B1");
        storeArea.setStorearea_id(3);
        DAOFactory.getFactory().getStoreAreaDAO().update(storeArea);
        assertTrue(storeArea.equals(DAOFactory.getFactory().getStoreAreaDAO().getById(3)));
    }

    @Ignore
    @Test
    public void deleteStoreAreaByIdTest() {
        DAOFactory.getFactory().getStoreAreaDAO().deleteById(4);
        storeArea = new StoreArea();
        assertFalse(storeArea.equals(DAOFactory.getFactory().getStoreAreaDAO().getById(4)));
    }

    @Ignore
    @Test
    public void readStoreAreaTest() {
        List<StoreArea> areas = DAOFactory.getFactory().getStoreAreaDAO().read();
        for (StoreArea area: areas) {
            System.out.println(area.toString());
        }
        assertTrue(areas.size() == 3);
    }
}