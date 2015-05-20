package service;

import configuration.DataConst;
import entity.Receiver;
import entity.Sender;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by User on 17.05.2015.
 */
public class CheckService {
    public static boolean isNullParam(Object ... param) {
        for (Object o : param) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRetentionLimitDate(String limit) {
        String[] limitStr = limit.split("\\.");
        Calendar inputDateLimit = new GregorianCalendar(Integer.parseInt(limitStr[2]), Integer.parseInt(limitStr[1]) - 1,
                Integer.parseInt(limitStr[0]));
        Calendar currentDate = new GregorianCalendar();
        Calendar nextDate = new GregorianCalendar();
        nextDate.add(Calendar.MONTH, 2);
        if (nextDate.getTime().getTime() >= inputDateLimit.getTime().getTime()
                && currentDate.getTime().getTime() <= inputDateLimit.getTime().getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static String checkPeriodForReport(String begin, String end) {
        String[] beginStr = begin.split("\\.");
        String[] endStr = end.split("\\.");
        String[] constBeginStr = DataConst.MIN_BEGIN_PERIOD_FOR_REPORT.split("\\.");
        String[] constEndStr = DataConst.MAX_END_PERIOD_FOR_REPORT.split("\\.");
        Calendar beginDate = new GregorianCalendar(Integer.parseInt(beginStr[2]), Integer.parseInt(beginStr[1]) - 1,
                Integer.parseInt(beginStr[0]));
        Calendar endDate = new GregorianCalendar(Integer.parseInt(endStr[2]), Integer.parseInt(endStr[1]) - 1,
                Integer.parseInt(endStr[0]));
        Calendar constBeginDate = new GregorianCalendar(Integer.parseInt(constBeginStr[2]),
                Integer.parseInt(constBeginStr[1]) - 1, Integer.parseInt(constBeginStr[0]));
        Calendar constEndDate = new GregorianCalendar(Integer.parseInt(constEndStr[2]),
                Integer.parseInt(constEndStr[1]) - 1, Integer.parseInt(constEndStr[0]));
        if (beginDate.getTime().getTime() >= endDate.getTime().getTime()) {
            return DataConst.INCORRECT_PERIOD_MESSAGE;
        }
        if (constBeginDate.getTime().getTime() > beginDate.getTime().getTime()) {
            return DataConst.INCORRECT_BEGIN_PERIOD_MESSAGE;
        }
        if (endDate.getTime().getTime() > constEndDate.getTime().getTime()) {
            return DataConst.INCORRECT_END_PERIOD_MESSAGE;
        }
        return null;
    }

    public static boolean checkEqualsWithoutId(Object ob1, Object ob2) {
        if (ob1 == null || ob2 == null) {
            return false;
        }
        if (ob1 instanceof Receiver) {
            Receiver receiver1 = (Receiver) ob1;
            Receiver receiver2 = (Receiver) ob2;
            if (receiver1.getReceiver_name().equals(receiver2.getReceiver_name())
                    && receiver1.getLegal_address().equals(receiver2.getLegal_address())
                    && receiver1.getEmail().equals(receiver2.getEmail())
                    && receiver1.getPhone().equals(receiver2.getPhone())) {
                return true;
            }
        } else {
            Sender sender1 = (Sender) ob1;
            Sender sender2 = (Sender) ob2;
            if (sender1.getSender_name().equals(sender2.getSender_name())
                    && sender1.getLegal_address().equals(sender2.getLegal_address())
                    && sender1.getEmail().equals(sender2.getEmail())
                    && sender1.getPhone().equals(sender2.getPhone())) {
                return true;
            }
        }
        return false;
    }
}
