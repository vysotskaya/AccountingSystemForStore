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

//    @Before
//    public void setSender() {
//        sender = new Sender("ytrewq@mail.ru", "Литва", "+375298150057", "OAO Металл");
//        DAOFactory.getFactory().getSenderDAO().create(sender);
//    }


    @Ignore
    @Test
    public void getSenderByIdTest() {
        sender.setSender_id(1);
        assertTrue(sender.equals(DAOFactory.getFactory().getSenderDAO().getById(1)));
    }

    @Ignore
    @Test
    public void getSenderByNameAndAddressTest() {
        Sender sender1 = DAOFactory.getFactory().getSenderDAO().getById(1);
        assertTrue(sender1.equals(DAOFactory.getFactory().getSenderDAO()
                .getSenderByLegalAddress(sender1.getLegal_address())));
    }

    @Ignore
    @Test
    public void updateSenderTest() {
        sender.setLegal_address("Россия");
        sender.setSender_id(1);
        DAOFactory.getFactory().getSenderDAO().update(sender);
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
        List<Sender> senders = DAOFactory.getFactory().getSenderDAO().read();
        for (Sender s : senders) {
            System.out.println(s.toString());
        }
        assertTrue(senders.size() == 1);
    }
}
