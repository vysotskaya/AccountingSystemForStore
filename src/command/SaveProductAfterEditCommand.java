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
 * Created by User on 13.05.2015.
 */
public class SaveProductAfterEditCommand implements Command {
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

        int receiver_id = Integer.parseInt((String) request.getParameter("receiver_id"));
        int sender_id = Integer.parseInt((String) request.getParameter("sender_id"));

        Record record = DAOFactory.getFactory().getRecordDAO()
                .getByProductId(DAOFactory.getFactory().getProductDAO().getProductByMarking(marking));

        String sender_name = (String) request.getParameter("senderNameInput");
        String sender_address = (String) request.getParameter("senderAddressInput");
        String sender_phone = (String) request.getParameter("senderPhoneInput");
        String sender_email = (String) request.getParameter("senderEmailInput");

        String receiver_name = (String) request.getParameter("receiverNameInput");
        String receiver_address = (String) request.getParameter("receiverAddressInput");
        String receiver_phone = (String) request.getParameter("receiverPhoneInput");
        String receiver_email = (String) request.getParameter("receiverEmailInput");

        Product product = DAOFactory.getFactory().getProductDAO().getProductByMarking(marking);
        product.setAcount(acount);
        product.setMeasuring_unit(measuring_unit);
        product.setCustomsRegimeType(DAOFactory.getFactory().getRegimeDAO().getById(regime_id));
        product.setStoring_features(features);

        Receiver receiver = DAOFactory.getFactory().getReceiverDAO().getById(receiver_id);
        receiver.setLegal_address(receiver_address);
        receiver.setEmail(receiver_email);
        receiver.setPhone(receiver_phone);
        receiver.setReceiver_name(receiver_name);

        Sender sender = DAOFactory.getFactory().getSenderDAO().getById(sender_id);
        sender.setLegal_address(sender_address);
        sender.setEmail(sender_email);
        sender.setPhone(sender_phone);
        sender.setSender_name(sender_name);

        if (true) {
            DAOFactory.getFactory().getProductDAO().update(product);
            DAOFactory.getFactory().getSenderDAO().update(sender);
            DAOFactory.getFactory().getReceiverDAO().update(receiver);

            record.setEmployee(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login));
            record.setProduct(product);
            record.setReceiver(receiver);
            record.setSender(sender);
            record.setRetention_limit(limit);
            record.setStoreArea(DAOFactory.getFactory().getStoreAreaDAO().getById(area_id));

            DAOFactory.getFactory().getRecordDAO().update(record);
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            request.setAttribute("isWrong", true);
            return PageManager.ADD_EMPLOYEE_COMMAND;
        }
        //return null;
    }
}
