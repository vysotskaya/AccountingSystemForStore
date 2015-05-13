package command;

import configuration.PageManager;
import service.DetentionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 13.05.2015.
 */
public class DetentionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List records = DetentionService.getProductsToDetention();
        request.setAttribute("list", records);
        return PageManager.PRODUCT_TO_DETENTION_PAGE;
    }
}
