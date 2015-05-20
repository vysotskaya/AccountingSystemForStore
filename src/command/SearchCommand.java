package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import service.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 19.05.2015.
 */
public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(SearchCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute(SessionAttribute.ROLE);
        if (role == null || role != DataConst.ADMIN_ID) {
            try {
                String findStr = (String) request.getParameter(RequestParam.SEARCH_OPTION);
                String page = (String) request.getParameter(RequestParam.CURRENT_PAGE);
                List resultList = null;
                Set resultSet = null;
                if (findStr.equals("") || findStr.replace(" ", "").equals("")) {
                    if (("/" + page).equals(PageManager.STORAGE_PAGE)) {
                        return PageManager.SHOW_ALL_RECORDS_COMMAND;
                    }
                    if (("/" + page).equals(PageManager.RECEIVER_PAGE)) {
                        return PageManager.SHOW_ALL_RECEIVERS_COMMAND;
                    }
                    if (("/" + page).equals(PageManager.SENDER_PAGE)) {
                        return PageManager.SHOW_ALL_SENDERS_COMMAND;
                    }
                    if (("/" + page).equals(PageManager.STORING_FEATURES_PAGE)) {
                        return PageManager.SHOW_STORING_FEATURES_COMMAND;
                    }
                    if (("/" + page).equals(PageManager.FORBIDDEN_PRODUCT_PAGE)) {
                        return PageManager.SHOW_FORBIDDEN_PRODUCT_COMMAND;
                    }
                    if (("/" + page).equals(PageManager.PRODUCT_PAGE)) {
                        return PageManager.SHOW_PRODUCT_COMMAND;
                    }
                    if (("/" + page).equals(PageManager.PRODUCT_TO_DETENTION_PAGE)) {
                        return PageManager.SHOW_PRODUCT_TO_DETENTION_COMMAND;
                    }
                    return PageManager.SHOW_ALL_RECORDS_COMMAND;
                } else {
                    if (("/" + page).equals(PageManager.STORAGE_PAGE)) {
                        resultSet = SearchService.getRecords(findStr);
                        if (resultSet == null) {
                            return PageManager.SHOW_ALL_RECORDS_COMMAND;
                        } else {
                            request.setAttribute(RequestParam.RESULT_LIST, resultSet);
                            return PageManager.STORAGE_PAGE;
                        }
                    }
                    if (("/" + page).equals(PageManager.RECEIVER_PAGE)) {
                        resultList = SearchService.getReceivers(findStr);
                        if (resultList != null) {
                            request.setAttribute(RequestParam.RESULT_LIST, resultList);
                            return PageManager.RECEIVER_PAGE;
                        } else {
                            return PageManager.SHOW_ALL_RECEIVERS_COMMAND;
                        }
                    }
                    if (("/" + page).equals(PageManager.SENDER_PAGE)) {
                        resultList = SearchService.getSenders(findStr);
                        if (resultList != null) {
                            request.setAttribute(RequestParam.RESULT_LIST, resultList);
                            return PageManager.SENDER_PAGE;
                        } else {
                            return PageManager.SHOW_ALL_SENDERS_COMMAND;
                        }
                    }
                    if (("/" + page).equals(PageManager.STORING_FEATURES_PAGE)) {
                        resultList = SearchService.getProductForFeatures(findStr);
                        if (resultList != null) {
                            request.setAttribute(RequestParam.RESULT_LIST, resultList);
                            return PageManager.STORING_FEATURES_PAGE;
                        } else {
                            return PageManager.SHOW_STORING_FEATURES_COMMAND;
                        }
                    }
                    if (("/" + page).equals(PageManager.FORBIDDEN_PRODUCT_PAGE)) {
                        resultSet = SearchService.getForbiddenProducts(findStr);
                        if (resultSet != null) {
                            request.setAttribute(RequestParam.RESULT_LIST, resultSet);
                            return PageManager.FORBIDDEN_PRODUCT_PAGE;
                        } else {
                            return PageManager.SHOW_FORBIDDEN_PRODUCT_COMMAND;
                        }
                    }
                    if (("/" + page).equals(PageManager.PRODUCT_PAGE)) {
                        resultSet = SearchService.getProducts(findStr);
                        if (resultSet != null) {
                            request.setAttribute(RequestParam.RESULT_LIST, resultSet);
                            return PageManager.PRODUCT_PAGE;
                        } else {
                            return PageManager.SHOW_PRODUCT_COMMAND;
                        }
                    }
                    if (("/" + page).equals(PageManager.PRODUCT_TO_DETENTION_PAGE)) {
                        resultSet = SearchService.getProductsToDetention(findStr);
                        if (resultSet != null) {
                            request.setAttribute(RequestParam.RESULT_LIST, resultSet);
                            return PageManager.PRODUCT_TO_DETENTION_PAGE;
                        } else {
                            return PageManager.SHOW_PRODUCT_TO_DETENTION_COMMAND;
                        }
                    }
                    return PageManager.SHOW_ALL_RECORDS_COMMAND;
                }
            } catch (HibernateException e) {
                logger.error("hibernate error" ,e);
            } catch (NullPointerException e) {
                logger.error("incorrect parameter : ", e);
            }
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
        }
    }
}
