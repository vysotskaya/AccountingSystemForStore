import dao.DAOFactory;
import entity.CustomsRegimeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by User on 07.04.2015.
 */
public class RegimeDAOTest {
    private CustomsRegimeType regime;

    @Before
    public void setRegime() {
        regime  = new CustomsRegimeType("свободное пользование");
        DAOFactory.getFactory().getRegimeDAO().create(regime);
    }

    @After
    public void clearRegime() {
        DAOFactory.getFactory().getRegimeDAO().deleteById(1);
    }

    @Ignore
    @Test
    public void getRegimeByIdTest() {
        regime.setRegime_id(1);
        assertTrue(regime.equals(DAOFactory.getFactory().getRegimeDAO().getById(1)));
    }

//    @Ignore
    @Test
    public void updateRegimeTest() {
        regime.setRegime_name("временный ввоз");
        DAOFactory.getFactory().getRegimeDAO().update(regime);
        regime.setRegime_id(1);
        assertTrue(regime.equals(DAOFactory.getFactory().getRegimeDAO().getById(1)));
    }

    @Ignore
    @Test
    public void deleteRegimeByIdTest() {
        DAOFactory.getFactory().getRegimeDAO().deleteById(1);
        regime = new CustomsRegimeType();
        assertFalse(regime.equals(DAOFactory.getFactory().getRegimeDAO().getById(1)));
    }

    @Ignore
    @Test
    public void readRegimeTest() {
        List regimes = DAOFactory.getFactory().getRegimeDAO().read();
        assertTrue(regimes.size() == 1);
    }
}
