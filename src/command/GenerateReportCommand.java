package command;

import configuration.PageManager;
import dao.DAOFactory;
import org.apache.log4j.Logger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
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
        Integer role = (Integer) session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if (role != 2) {
                String periodBegin = (String) request.getParameter("periodBeginInput");
                String periodEnd = (String) request.getParameter("periodEndInput");

                if (periodBegin == null || periodEnd == null) {
                    return PageManager.LOGIN_PAGE;
                }

                List records = DAOFactory.getFactory().getRecordDAO().getRecordsForPeriod(periodBegin, periodEnd);
                if (records != null) {
                    try {
                        GenerateReportService.generateReport(periodBegin, periodEnd, records);
                    } catch (Docx4JException e) {
                        logger.error("error in generating report : ", e);
                    }
                }
                return PageManager.SHOW_ALL_RECORDS_COMMAND;

            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
