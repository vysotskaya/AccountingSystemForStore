package command;

import configuration.PageManager;
import dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 11.05.2015.
 */
public class AddProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List regimes = DAOFactory.getFactory().getRegimeDAO().read();
        request.setAttribute("regimeList", regimes);

        List areas = DAOFactory.getFactory().getStoreAreaDAO().read();
        request.setAttribute("areaList", areas);

        return PageManager.ADD_PRODUCT_PAGE;
    }
}
