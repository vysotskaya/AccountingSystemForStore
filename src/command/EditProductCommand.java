package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Record;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class EditProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt((String)request.getParameter("record_id"));
        Record record = DAOFactory.getFactory().getRecordDAO().getById(id);

        List regimes = DAOFactory.getFactory().getRegimeDAO().read();
        request.setAttribute("regimeList", regimes);

        List areas = DAOFactory.getFactory().getStoreAreaDAO().read();
        request.setAttribute("areaList", areas);

        request.setAttribute("record", record);

        return PageManager.EDIT_PRODUCT_PAGE;
    }
}
