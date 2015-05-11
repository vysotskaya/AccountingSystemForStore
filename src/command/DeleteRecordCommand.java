package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 11.05.2015.
 */
public class DeleteRecordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int record_id = Integer.parseInt((String)request.getParameter("record_id"));
        int product_id = DAOFactory.getFactory().getRecordDAO().getById(record_id).getProduct().getProduct_id();
        DAOFactory.getFactory().getRecordDAO().deleteById(record_id);
        DAOFactory.getFactory().getProductDAO().deleteById(product_id);
        return PageManager.SHOW_ALL_RECORDS_COMMAND;
    }
}
