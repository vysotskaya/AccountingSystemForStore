package command;

import configuration.PageManager;
import dao.DAOFactory;
import entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by User on 11.05.2015.
 */
public class AddProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer role = (Integer)session.getAttribute("role");
        if (role == null) {
            return PageManager.LOGIN_PAGE;
        } else {
            if ( role != 2) {
                List regimes = DAOFactory.getFactory().getRegimeDAO().read();
                request.setAttribute("regimeList", regimes);

                List areas = DAOFactory.getFactory().getStoreAreaDAO().read();
                request.setAttribute("areaList", areas);

                return PageManager.ADD_PRODUCT_PAGE;
            } else {
                return PageManager.SHOW_ALL_EMPLOYEES_COMMAND;
            }
        }
    }
}
