package command;

import configuration.DataConst;
import configuration.PageManager;
import configuration.RequestParam;
import configuration.SessionAttribute;
import dao.DAOFactory;
import entity.Record;
import org.apache.log4j.Logger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.hibernate.HibernateException;
import service.CheckService;
import service.GenerateReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 12.05.2015.
 */
public class GenerateReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(GenerateReportCommand.class);
        HttpSession session = request.getSession();
        Integer role = (Integer) session.getAttribute(SessionAttribute.ROLE);
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if (role != DataConst.ADMIN_ID) {
                try {
                    String periodBegin = (String) request.getParameter(RequestParam.BEGIN_PERIOD_FOR_REPORT);
                    String periodEnd = (String) request.getParameter(RequestParam.END_PERIOD_FOR_REPORT);

                    if (CheckService.isNullParam(periodBegin, periodEnd)) {
                        return PageManager.LOGIN_PAGE;
                    }

                    String checkString = CheckService.checkPeriodForReport(periodBegin, periodEnd);
                    if (checkString != null) {
                        request.setAttribute(RequestParam.ERROR_MESSAGE, checkString);
                        request.setAttribute(RequestParam.INCORRECT_DATA, true);
                        request.setAttribute(RequestParam.BEGIN, periodBegin);
                        request.setAttribute(RequestParam.END, periodEnd);
                        return PageManager.PERIOD_PAGE;
                    }

                    List<Record> records = DAOFactory.getFactory().getRecordDAO().getRecordsForPeriod(periodBegin, periodEnd);
                    if (records != null) {
                        try {
                            GenerateReportService.generateReport(periodBegin, periodEnd, records);
                        } catch (Docx4JException e) {
                            logger.error("error in generating report : ", e);
                        }
                    } else {
                        request.setAttribute(RequestParam.ERROR_MESSAGE, DataConst.NO_PRODUCT_MESSAGE);
                        request.setAttribute(RequestParam.INCORRECT_DATA, true);
                        request.setAttribute(RequestParam.BEGIN, periodBegin);
                        request.setAttribute(RequestParam.END, periodEnd);
                        return PageManager.PERIOD_PAGE;
                    }
                } catch (HibernateException e) {
                    logger.error("hibernate error", e);
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;

            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
