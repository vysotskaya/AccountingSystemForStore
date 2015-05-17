package service;

import entity.Receiver;
import entity.Sender;

import java.util.Calendar;
import java.util.Date;
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
