package service;

import dao.DAOFactory;
import entity.Record;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class DetentionService {
    private static final String periodBegin = "01.01.1000";

    public static List<Record> getProductsToDetention() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<Record> recordList = DAOFactory.getFactory().getRecordDAO()
                .getRecordsForPeriod(periodBegin, sdf.format(currentDate).toString());
        if (!recordList.isEmpty()) {
            return recordList;
        }
        return null;
    }
}
