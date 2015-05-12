package command;

import configuration.PageManager;
import dao.DAOFactory;
import service.GenerateReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 12.05.2015.
 */
public class GenerateReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String periodBegin = (String) request.getParameter("periodBeginInput");
        String periodEnd = (String) request.getParameter("periodEndInput");

        List records = DAOFactory.getFactory().getRecordDAO().getRecordsForPeriod(periodBegin, periodEnd);
        if (records != null) {
            GenerateReportService.generateReport(periodBegin, periodEnd, records);
        }
        return PageManager.SHOW_ALL_RECORDS_COMMAND;
    }
}
