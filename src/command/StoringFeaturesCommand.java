package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class StoringFeaturesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List products = DAOFactory.getFactory().getProductDAO().read();
        request.setAttribute("list", products);
        return PageManager.STORING_FEATURES_PAGE;
    }
}
