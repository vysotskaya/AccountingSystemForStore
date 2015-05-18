package service;

import dao.DAOFactory;
import entity.Record;
import org.hibernate.HibernateException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class DetentionService {
    private static final String PERIOD_BEGIN = "01.01.1000";

    public static List<Record> getProductsToDetention() throws HibernateException{
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<Record> recordList = DAOFactory.getFactory().getRecordDAO()
                .getRecordsForPeriod(PERIOD_BEGIN, sdf.format(currentDate).toString());
        return recordList;
    }
}
