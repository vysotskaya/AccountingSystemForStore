package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Product;
import entity.Receiver;
import entity.Record;
import entity.Sender;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 11.05.2015.
 */
public class SaveProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = null;
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role != 2) {
                try {
                    String employee_login = (String)session.getAttribute("login");

                    String marking = (String) request.getParameter("markingInput");
                    int acount = Integer.parseInt((String) request.getParameter("acountInput"));
                    String product_name = (String) request.getParameter("nameInput");
                    String measuring_unit = (String) request.getParameter("unitSelect");
                    String limit = (String) request.getParameter("limitInput");
                    String features = (String) request.getParameter("featuresInput");
                    int regime_id = Integer.parseInt((String) request.getParameter("regimeSelect"));
                    int area_id = Integer.parseInt((String) request.getParameter("areaSelect"));

                    String sender_name = (String) request.getParameter("senderNameInput");
                    String sender_address = (String) request.getParameter("senderAddressInput");
                    String sender_phone = (String) request.getParameter("senderPhoneInput");
                    String sender_email = (String) request.getParameter("senderEmailInput");

                    String receiver_name = (String) request.getParameter("receiverNameInput");
                    String receiver_address = (String) request.getParameter("receiverAddressInput");
                    String receiver_phone = (String) request.getParameter("receiverPhoneInput");
                    String receiver_email = (String) request.getParameter("receiverEmailInput");

                    if (marking == null || product_name == null || measuring_unit == null || limit == null
                            || features == null || sender_name == null || sender_address  == null
                            || sender_phone  == null || sender_email  == null || receiver_name  == null
                            || receiver_email  == null || receiver_address  == null || receiver_phone  == null) {
                        throw new NullPointerException();
                    }

                    Product product = new Product(acount, DAOFactory.getFactory().getRegimeDAO().getById(regime_id),
                            measuring_unit, marking, features, product_name);
                    Receiver receiver = new Receiver(receiver_email, receiver_address, receiver_phone, receiver_name);
                    Sender sender = new Sender(sender_email, sender_address, sender_phone, sender_name);
                    Record record = new Record(
                            DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login),
                            product, receiver, limit, DAOFactory.getFactory().getStoreAreaDAO().getById(area_id), sender);

                    if (DAOFactory.getFactory().getProductDAO().getProductByMarking(marking) == null) {
                        DAOFactory.getFactory().getProductDAO().create(product);
                        DAOFactory.getFactory().getSenderDAO().create(sender);
                        DAOFactory.getFactory().getReceiverDAO().create(receiver);
                        DAOFactory.getFactory().getRecordDAO().create(record);
                    } else {
                        List regimes = DAOFactory.getFactory().getRegimeDAO().read();
                        request.setAttribute("regimeList", regimes);

                        List areas = DAOFactory.getFactory().getStoreAreaDAO().read();
                        request.setAttribute("areaList", areas);

                        request.setAttribute("isWrong", true);
                        request.setAttribute("record", record);
                        return PageManager.ADD_PRODUCT_PAGE;
                    }
                } catch (NullPointerException ex) {
                    logger = Logger.getLogger(SaveProductCommand.class);
                    logger.error("incorrect data in saving product", ex);
                } catch (NumberFormatException ex) {
                    logger = Logger.getLogger(SaveProductCommand.class);
                    logger.error("incorrect format of area_id, regime_id or acount", ex);
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
