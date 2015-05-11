package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Product;
import entity.Receiver;
import entity.Record;
import entity.Sender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 11.05.2015.
 */
public class SaveProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
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

        Product product = new Product(acount, DAOFactory.getFactory().getRegimeDAO().getById(regime_id),
                measuring_unit, marking, features, product_name);
        Receiver receiver = new Receiver(receiver_email, receiver_address, receiver_phone, receiver_name);
        Sender sender = new Sender(sender_email, sender_address, sender_phone, sender_name);

        if (DAOFactory.getFactory().getProductDAO().getProductByMarking(marking) == null) {
            DAOFactory.getFactory().getProductDAO().create(product);
            DAOFactory.getFactory().getSenderDAO().create(sender);
            DAOFactory.getFactory().getReceiverDAO().create(receiver);
            DAOFactory.getFactory().getRecordDAO().create(new Record(
                    DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login),
                    product, receiver, limit, DAOFactory.getFactory().getStoreAreaDAO().getById(area_id), sender));
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            request.setAttribute("isWrong", true);
            return PageManager.ADD_EMPLOYEE_COMMAND;
        }
        //return null;
    }
}
