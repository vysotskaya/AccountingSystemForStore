import dao.DAOFactory;
import entity.Receiver;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by User on 08.04.2015.
 */
public class ReceiverDAOTest {
    private Receiver receiver;

    @Before
    public void setReceiver() {
        receiver = new Receiver("qwerty@mail.ru", "Беларусь", "+375298150057", "OAO Металл");
        DAOFactory.getFactory().getReceiverDAO().create(receiver);
    }

    @After
    public void clearReceiver() {
        DAOFactory.getFactory().getReceiverDAO().deleteById(1);
    }

    @Ignore
    @Test
    public void getReceiverByIdTest() {
        receiver.setReceiver_id(1);
        assertTrue(receiver.equals(DAOFactory.getFactory().getReceiverDAO().getById(1)));
    }

    @Ignore
    @Test
    public void updateReceiverTest() {
        receiver.setLegal_address("Россия");
        DAOFactory.getFactory().getReceiverDAO().update(receiver);
        receiver.setReceiver_id(1);
        assertTrue(receiver.equals(DAOFactory.getFactory().getReceiverDAO().getById(1)));
    }

    @Ignore
    @Test
    public void deleteReceiverByIdTest() {
        DAOFactory.getFactory().getReceiverDAO().deleteById(1);
        receiver = new Receiver();
        assertFalse(receiver.equals(DAOFactory.getFactory().getReceiverDAO().getById(1)));
    }

//    @Ignore
    @Test
    public void readReceiverTest() {
        List receivers = DAOFactory.getFactory().getReceiverDAO().read();
        assertTrue(receivers.size() == 1);
    }
}
