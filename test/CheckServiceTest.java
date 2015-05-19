import org.junit.Ignore;
import org.junit.Test;
import service.CheckService;

import javax.swing.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 17.05.2015.
 */
public class CheckServiceTest {
    @Ignore
    @Test
    public void checkRetentionLimitDateTest() {
        String limit = "01.07.2015";
        assertTrue(CheckService.checkRetentionLimitDate(limit));
    }

    @Test
    public void saveDialogTest() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showSaveDialog(jFrame);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            System.out.println(file.getName());
            System.out.println(file.getAbsolutePath());
        }

    }
}
