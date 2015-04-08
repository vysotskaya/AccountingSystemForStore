import dao.DAOFactory;
import entity.Sender;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by User on 08.04.2015.
 */
public class SenderDAOTest {
    private Sender sender;

    @Before
    public void setSender() {
        sender = new Sender("ytrewq@mail.ru", "Литва", "+375298150057", "OAO Металл");
        DAOFactory.getFactory().getSenderDAO().create(sender);
    }

    @After
    public void clearSender() {
        DAOFactory.getFactory().getSenderDAO().deleteById(1);
    }

//    @Ignore
    @Test
    public void getSenderByIdTest() {
        sender.setSender_id(1);
        assertTrue(sender.equals(DAOFactory.getFactory().getSenderDAO().getById(1)));
    }

    @Ignore
    @Test
    public void updateSenderTest() {
        sender.setLegal_address("Россия");
        DAOFactory.getFactory().getSenderDAO().update(sender);
        sender.setSender_id(1);
        assertTrue(sender.equals(DAOFactory.getFactory().getSenderDAO().getById(1)));
    }

    @Ignore
    @Test
    public void deleteSenderByIdTest() {
        DAOFactory.getFactory().getSenderDAO().deleteById(1);
        sender = new Sender();
        assertFalse(sender.equals(DAOFactory.getFactory().getSenderDAO().getById(1)));
    }

    @Ignore
    @Test
    public void readSenderTest() {
        List senders = DAOFactory.getFactory().getSenderDAO().read();
        assertTrue(senders.size() == 1);
    }
}