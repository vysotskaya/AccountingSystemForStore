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

//    @Before
//    public void setRegime() {
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("свободное обращение"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("экспорт"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("переработка на таможенной территории"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("переработка вне таможенной территории"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("временный ввоз"));
//
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("временный вывоз"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("таможенный склад"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("реимпорт"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("реэкспорт"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("уничтожение"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("отказ в пользу государства"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("свободная таможенная зона"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("беспошлинная торговля"));
//        DAOFactory.getFactory().getRegimeDAO().create(new CustomsRegimeType("свободный склад"));
//    }

//
//    @After
//    public void clearRegime() {
//        DAOFactory.getFactory().getRegimeDAO().deleteById(1);
//    }

    @Ignore
    @Test
    public void getRegimeByIdTest() {
        regime.setRegime_id(1);
        assertTrue(regime.equals(DAOFactory.getFactory().getRegimeDAO().getById(1)));
    }

    @Ignore
    @Test
    public void getRegimeByNameTest() {
        CustomsRegimeType regimeType = DAOFactory.getFactory().getRegimeDAO().getById(3);
        assertTrue(regimeType.equals(DAOFactory.getFactory().getRegimeDAO()
                .getRegimeByName(regimeType.getRegime_name())));
    }

    @Ignore
    @Test
    public void updateRegimeTest() {
        regime.setRegime_name("временный ввоз");
        regime.setRegime_id(1);
        DAOFactory.getFactory().getRegimeDAO().update(regime);
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
        List<CustomsRegimeType> regimes = DAOFactory.getFactory().getRegimeDAO().read();
        for (CustomsRegimeType customsRegimeType : regimes) {
            System.out.println(customsRegimeType.toString());
        }
        assertTrue(regimes.size() == 14);
    }
}
