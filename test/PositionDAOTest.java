import dao.DAOFactory;
import entity.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by User on 07.04.2015.
 */
public class PositionDAOTest {
    private Position position;

    @Before
    public void setPosition() {
        position = new Position("инженер");
        DAOFactory.getFactory().getPositionDAO().create(position);
    }

    @After
    public void clearPosition() {
        DAOFactory.getFactory().getPositionDAO().deleteById(1);
    }

    @Ignore
    @Test
    public void getPositionByIdTest() {
        position.setPosition_id(1);
        assertTrue(position.equals(DAOFactory.getFactory().getPositionDAO().getById(1)));
    }

    @Ignore
    @Test
    public void updatePositionTest() {
        position.setPosition_name("директор");
        position.setPosition_id(1);
        DAOFactory.getFactory().getPositionDAO().update(position);
        assertTrue(position.equals(DAOFactory.getFactory().getPositionDAO().getById(1)));
    }

//    @Ignore
    @Test
    public void deletePositionByIdTest() {
        DAOFactory.getFactory().getPositionDAO().deleteById(1);
        position = new Position();
        assertFalse(position.equals(DAOFactory.getFactory().getPositionDAO().getById(1)));
    }

//    @Ignore
    @Test
    public void readPositionTest() {
        List positions = DAOFactory.getFactory().getPositionDAO().read();
        assertTrue(positions.size() == 1);
    }
}
