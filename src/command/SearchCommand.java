package command;

import configuration.PageManager;
import entity.Record;
import service.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by User on 14.05.2015.
 */
public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String findStr = (String) request.getParameter("searchOption");
        if (findStr.equals("")) {
            return PageManager.SHOW_ALL_RECORDS_COMMAND;
        } else {
            Set<Record> recordSet = SearchService.getRecords(findStr);
            request.setAttribute("list", recordSet);
            return PageManager.STORAGE_PAGE;
        }
    }
}
