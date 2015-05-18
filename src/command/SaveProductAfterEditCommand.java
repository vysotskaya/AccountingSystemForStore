package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Product;
import entity.Receiver;
import entity.Record;
import entity.Sender;
import exception.IncorrectDataInputException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.CheckService;
import service.CreationListsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 13.05.2015.
 */
public class SaveProductAfterEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SaveProductAfterEditCommand.class);
        HttpSession session = request.getSession();
        Record record = null;
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

                    int receiver_id = Integer.parseInt((String) request.getParameter("receiver_id"));
                    int sender_id = Integer.parseInt((String) request.getParameter("sender_id"));

                    String sender_name = (String) request.getParameter("senderNameInput");
                    String sender_address = (String) request.getParameter("senderAddressInput");
                    String sender_phone = (String) request.getParameter("senderPhoneInput");
                    String sender_email = (String) request.getParameter("senderEmailInput");

                    String receiver_name = (String) request.getParameter("receiverNameInput");
                    String receiver_address = (String) request.getParameter("receiverAddressInput");
                    String receiver_phone = (String) request.getParameter("receiverPhoneInput");
                    String receiver_email = (String) request.getParameter("receiverEmailInput");

                    if (CheckService.isNullParam(marking, product_name, measuring_unit, limit, features,
                            sender_name, sender_address, sender_phone, sender_email, receiver_name,
                            receiver_email, receiver_address, receiver_phone)) {
                        throw new NullPointerException();
                    }

                    if (features.equals("")) {
                        features = "отсутствуют";
                    }

                    Product product = DAOFactory.getFactory().getProductDAO().getProductByMarking(marking);

                    record = DAOFactory.getFactory().getRecordDAO().getByProductId(product);

                    product.setProduct_name(product_name);
                    product.setAcount(acount);
                    product.setMeasuring_unit(measuring_unit);
                    product.setCustomsRegimeType(DAOFactory.getFactory().getRegimeDAO().getById(regime_id));
                    product.setStoring_features(features);

                    if (!CheckService.checkRetentionLimitDate(limit)){
                        throw new IncorrectDataInputException("Неверный срок хранения!");
                    }

                    Sender sender = DAOFactory.getFactory().getSenderDAO().getById(sender_id);
                    Sender senderByLegalAddress = DAOFactory.getFactory().getSenderDAO()
                            .getSenderByLegalAddress(sender_address);

                    if (!sender.equals(senderByLegalAddress)) {
                        if (senderByLegalAddress != null) {
                            record.setSender(senderByLegalAddress);
                            throw new IncorrectDataInputException("Отправитель с таким юридическим адресом уже существует! " +
                                    "Вы можете выбрать его, сохранив запись повторно.");
                        } else {
                            sender.setLegal_address(sender_address);
                            sender.setEmail(sender_email);
                            sender.setPhone(sender_phone);
                            sender.setSender_name(sender_name);
                            DAOFactory.getFactory().getSenderDAO().update(sender);
                        }
                    } else {
                        sender.setEmail(sender_email);
                        sender.setPhone(sender_phone);
                        sender.setSender_name(sender_name);
                        DAOFactory.getFactory().getSenderDAO().update(sender);
                    }

                    Receiver receiver = DAOFactory.getFactory().getReceiverDAO().getById(receiver_id);

                    Receiver receiverByLegalAddress = DAOFactory.getFactory().getReceiverDAO()
                            .getReceiverByLegalAddress(receiver_address);

                    if (!receiver.equals(receiverByLegalAddress)) {
                        if (receiverByLegalAddress != null) {
                            record.setReceiver(receiverByLegalAddress);
                            throw new IncorrectDataInputException("Получатель с таким юридическим адресом уже существует! " +
                                    "Вы можете выбрать его, сохранив запись повторно.");
                        } else {
                            receiver.setLegal_address(receiver_address);
                            receiver.setEmail(receiver_email);
                            receiver.setPhone(receiver_phone);
                            receiver.setReceiver_name(receiver_name);
                            DAOFactory.getFactory().getReceiverDAO().update(receiver);
                        }
                    } else {
                        receiver.setEmail(receiver_email);
                        receiver.setPhone(receiver_phone);
                        receiver.setReceiver_name(receiver_name);
                        DAOFactory.getFactory().getReceiverDAO().update(receiver);
                    }

                    DAOFactory.getFactory().getProductDAO().update(product);
                    record.setEmployee(DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login));
                    record.setProduct(product);
                    record.setReceiver(receiver);
                    record.setSender(sender);
                    record.setRetention_limit(limit);
                    record.setStoreArea(DAOFactory.getFactory().getStoreAreaDAO().getById(area_id));

                    DAOFactory.getFactory().getRecordDAO().update(record);
                    return PageManager.SHOW_ALL_RECORDS_COMMAND;

                } catch (HibernateException e) {
                    logger.error("hibernate error", e);
                } catch (NullPointerException ex) {
                    logger.error("incorrect data in saving product", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of receiver_id, sender_id, regime_id, area_id or acount", ex);
                } catch (IncorrectDataInputException e) {
                    CreationListsService.createRegimesAndAreasLists(request);
                    request.setAttribute("errorMessage", e.getMessage());
                    request.setAttribute("isWrong", true);
                    request.setAttribute("record", record);
                    return PageManager.EDIT_PRODUCT_PAGE;
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
