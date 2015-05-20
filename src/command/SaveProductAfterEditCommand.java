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
import javax.xml.crypto.Data;

/**
 * Created by User on 13.05.2015.
 */
public class SaveProductAfterEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SaveProductAfterEditCommand.class);
        HttpSession session = request.getSession();
        Record record = null;
        Integer role = (Integer)session.getAttribute(SessionAttribute.ROLE);
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

                    int receiver_id = Integer.parseInt((String) request.getParameter(RequestParam.RECEIVER_ID));
                    int sender_id = Integer.parseInt((String) request.getParameter(RequestParam.SENDER_ID));

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

                    Product product = DAOFactory.getFactory().getProductDAO().getProductByMarking(marking);

                    record = DAOFactory.getFactory().getRecordDAO().getByProductId(product);

                    product.setProduct_name(product_name);
                    product.setAcount(acount);
                    product.setMeasuring_unit(measuring_unit);
                    product.setCustomsRegimeType(DAOFactory.getFactory().getRegimeDAO().getById(regime_id));
                    product.setStoring_features(features);

                    if (!CheckService.checkRetentionLimitDate(limit)){
                        throw new IncorrectDataInputException(DataConst.INCORRECT_LIMIT_MESSAGE);
                    }

                    Sender sender = DAOFactory.getFactory().getSenderDAO().getById(sender_id);
                    Sender senderByLegalAddress = DAOFactory.getFactory().getSenderDAO()
                            .getSenderByLegalAddress(sender_address);

                    if (!sender.equals(senderByLegalAddress)) {
                        if (senderByLegalAddress != null) {
                            record.setSender(senderByLegalAddress);
                            throw new IncorrectDataInputException(DataConst.DUPLICATION_SENDER_MESSAGE);
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
                            throw new IncorrectDataInputException(DataConst.DUPLICATION_RECEIVER_MESSAGE);
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
                    request.setAttribute(RequestParam.ERROR_MESSAGE, e.getMessage());
                    request.setAttribute(RequestParam.INCORRECT_DATA, true);
                    request.setAttribute(RequestParam.RECORD, record);
                    return PageManager.EDIT_PRODUCT_PAGE;
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
