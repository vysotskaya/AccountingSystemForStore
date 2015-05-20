package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
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
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
        Record record = null;
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role != DataConst.ADMIN_ID) {
                try {
                    String employee_login = (String)session.getAttribute(SessionAttribute.LOGIN);

                    String marking = (String) request.getParameter(RequestParam.PRODUCT_MARKING_INPUT);
                    int acount = Integer.parseInt((String) request.getParameter(RequestParam.PRODUCT_ACOUNT_INPUT));
                    String product_name = (String) request.getParameter(RequestParam.PRODUCT_NAME_INPUT);
                    String measuring_unit = (String) request.getParameter(RequestParam.PRODUCT_UNIT_SELECT);
                    String limit = (String) request.getParameter(RequestParam.PRODUCT_LIMIT_INPUT);
                    String features = (String) request.getParameter(RequestParam.PRODUCT_FEARURES_INPUT);
                    int regime_id = Integer.parseInt((String) request.getParameter(RequestParam.PRODUCT_REGIME_SELECT));
                    int area_id = Integer.parseInt((String) request.getParameter(RequestParam.PRODUCT_AREA_SELECT));

                    String sender_name = (String) request.getParameter(RequestParam.SENDER_NAME_INPUT);
                    String sender_address = (String) request.getParameter(RequestParam.SENDER_ADDRESS_INPUT);
                    String sender_phone = (String) request.getParameter(RequestParam.SENDER_PHONE_INPUT);
                    String sender_email = (String) request.getParameter(RequestParam.SENDER_EMAIL_INPUT);

                    String receiver_name = (String) request.getParameter(RequestParam.RECEIVER_NAME_INPUT);
                    String receiver_address = (String) request.getParameter(RequestParam.RECEIVER_ADDRESS_INPUT);
                    String receiver_phone = (String) request.getParameter(RequestParam.RECEIVER_PHONE_INPUT);
                    String receiver_email = (String) request.getParameter(RequestParam.RECEIVER_EMAIL_INPUT);

                    if (CheckService.isNullParam(marking, product_name, measuring_unit, limit, features,
                            sender_name, sender_address, sender_phone, sender_email, receiver_name,
                            receiver_email, receiver_address, receiver_phone)) {
                        throw new NullPointerException();
                    }

                    if (features.equals("")) {
                        features = DataConst.NO_FEATURES;
                    }

                    Product product = new Product(acount, DAOFactory.getFactory().getRegimeDAO().getById(regime_id),
                            measuring_unit, marking, features, product_name);

                    Receiver receiver = new Receiver(receiver_email, receiver_address, receiver_phone, receiver_name);
                    Sender sender = new Sender(sender_email, sender_address, sender_phone, sender_name);
                    record = new Record();
                    record = new Record(
                            DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login),
                            product, receiver, limit, DAOFactory.getFactory().getStoreAreaDAO().getById(area_id), sender);

                    if (!CheckService.checkRetentionLimitDate(limit)){
                        throw new IncorrectDataInputException(DataConst.INCORRECT_LIMIT_MESSAGE);
                    }

                    if (DAOFactory.getFactory().getProductDAO().getProductByMarking(marking) != null) {
                        throw new IncorrectDataInputException(DataConst.DUPLICATION_PRODUCT_MARKING_MESSAGE);
                    }

                    Sender senderByLegalAddress = DAOFactory.getFactory().getSenderDAO()
                            .getSenderByLegalAddress(sender_address);

                    if (!CheckService.checkEqualsWithoutId(sender, senderByLegalAddress)) {
                        if (senderByLegalAddress != null) {
                            record.setSender(senderByLegalAddress);
                            throw new IncorrectDataInputException(DataConst.DUPLICATION_SENDER_MESSAGE);
                        } else {
                            DAOFactory.getFactory().getSenderDAO().create(sender);
                        }
                    }

                    Receiver receiverByLegalAddress = DAOFactory.getFactory().getReceiverDAO()
                            .getReceiverByLegalAddress(receiver_address);

                    if (!CheckService.checkEqualsWithoutId(receiver, receiverByLegalAddress)) {
                        if (receiverByLegalAddress != null) {
                            record.setReceiver(receiverByLegalAddress);
                            throw new IncorrectDataInputException(DataConst.DUPLICATION_RECEIVER_MESSAGE);
                        } else {
                            DAOFactory.getFactory().getReceiverDAO().create(receiver);
                        }
                    }

                    DAOFactory.getFactory().getProductDAO().create(product);
                    record = new Record(
                            DAOFactory.getFactory().getEmployeeDAO().getEmployeeByLogin(employee_login),
                            DAOFactory.getFactory().getProductDAO().getProductByMarking(marking),
                            DAOFactory.getFactory().getReceiverDAO().getReceiverByLegalAddress(receiver_address), limit,
                            DAOFactory.getFactory().getStoreAreaDAO().getById(area_id),
                            DAOFactory.getFactory().getSenderDAO().getSenderByLegalAddress(sender_address));
                    DAOFactory.getFactory().getRecordDAO().create(record);

                } catch (HibernateException e) {
                    logger.error("hibernate error", e);
                } catch (NullPointerException ex) {
                    logger.error("incorrect data in saving product", ex);
                } catch (NumberFormatException ex) {
                    logger.error("incorrect format of area_id, regime_id or acount", ex);
                } catch (IncorrectDataInputException e) {
                    CreationListsService.createRegimesAndAreasLists(request);
                    request.setAttribute(RequestParam.ERROR_MESSAGE, e.getMessage());
                    request.setAttribute(RequestParam.INCORRECT_DATA, true);
                    request.setAttribute(RequestParam.RECORD, record);
                    return PageManager.ADD_PRODUCT_PAGE;
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
