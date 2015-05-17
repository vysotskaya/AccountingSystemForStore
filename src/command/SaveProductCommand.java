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
 * Created by User on 11.05.2015.
 */
public class SaveProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SaveProductCommand.class);;
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        Record record = null;
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

                    if (CheckService.isNullParam(marking, product_name, measuring_unit, limit, features,
                            sender_name, sender_address, sender_phone, sender_email, receiver_name,
                            receiver_email, receiver_address, receiver_phone)) {
                        throw new NullPointerException();
                    }

                    if (features.equals("")) {
                        features = "отсутствуют";
                    }

                    Product product = new Product(acount, DAOFactory.getFactory().getRegimeDAO().getById(regime_id),
                            measuring_unit, marking, features, product_name);

                    Receiver receiver = new Receiver(receiver_email, receiver_address, receiver_phone, receiver_name);
                    Sender sender = new Sender(sender_email, sender_address, sender_phone, sender_name);
                    record = new Record(
                            DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login),
                            product, receiver, limit, DAOFactory.getFactory().getStoreAreaDAO().getById(area_id), sender);

                    if (!CheckService.checkRetentionLimitDate(limit)){
                        throw new IncorrectDataInputException("Неверный срок хранения!");
                    }

                    if (DAOFactory.getFactory().getProductDAO().getProductByMarking(marking) != null) {
                        throw new IncorrectDataInputException("Товар с такой маркировкой уже существует!");
                    }

                    Sender senderByLegalAddress = DAOFactory.getFactory().getSenderDAO()
                            .getSenderByLegalAddress(sender_address);

                    if (!CheckService.checkEqualsWithoutId(sender, senderByLegalAddress)) {
                        if (senderByLegalAddress != null) {
                            record.setSender(senderByLegalAddress);
                            throw new IncorrectDataInputException("Отправитель с таким юридическим адресом уже существует! " +
                                    "Вы можете выбрать его, сохранив запись повторно.");
                        } else {
                            DAOFactory.getFactory().getSenderDAO().create(sender);
                        }
                    }

                    Receiver receiverByLegalAddress = DAOFactory.getFactory().getReceiverDAO()
                            .getReceiverByLegalAddress(receiver_address);

                    if (!CheckService.checkEqualsWithoutId(receiver, receiverByLegalAddress)) {
                        if (receiverByLegalAddress != null) {
                            record.setReceiver(receiverByLegalAddress);
                            throw new IncorrectDataInputException("Получатель с таким юридическим адресом уже существует! " +
                                    "Вы можете выбрать его, сохранив запись повторно.");
                        } else {
                            DAOFactory.getFactory().getReceiverDAO().create(receiver);
                        }
                    }

                    DAOFactory.getFactory().getProductDAO().create(product);
                    DAOFactory.getFactory().getRecordDAO().create(record);

                } catch (HibernateException e) {
                    logger.error("hibernate error", e);
                } catch (NullPointerException ex) {
                    logger.error("incorrect data in saving product", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of area_id, regime_id or acount", ex);
                } catch (IncorrectDataInputException e) {
                    CreationListsService.createRegimesAndAreasLists(request);
                    request.setAttribute("errorMessage", e.getMessage());
                    request.setAttribute("isWrong", true);
                    request.setAttribute("record", record);
                    return PageManager.ADD_PRODUCT_PAGE;
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
