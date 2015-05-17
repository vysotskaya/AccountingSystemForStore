import org.junit.Test;
import service.CheckService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 17.05.2015.
 */
public class CheckServiceTest {
    @Test
    public void checkRetentionLimitDateTest() {
        String limit = "01.07.2015";
        assertTrue(CheckService.checkRetentionLimitDate(limit));
    }
}
