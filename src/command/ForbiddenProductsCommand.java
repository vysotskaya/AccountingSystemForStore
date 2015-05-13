package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class ForbiddenProductsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List records = DAOFactory.getFactory().getRecordDAO().getRecordsByProductRegime("уничтожение");
        request.setAttribute("list", records);
        return PageManager.FORBIDDEN_PRODUCT_PAGE;
    }
}
