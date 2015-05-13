package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class ProductsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List records = DAOFactory.getFactory().getRecordDAO().read();
        request.setAttribute("list", records);
        return PageManager.PRODUCT_PAGE;
    }
}
