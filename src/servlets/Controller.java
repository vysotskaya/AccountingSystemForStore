package servlets;

import command.Command;
import command.manage.CommandFactory;
import configuration.PageManager;
import dao.DAOFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 21.04.2015.
 */
public class Controller extends HttpServlet {

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String prefix = getServletContext().getRealPath("/");
        String filename = getInitParameter("log4j.xml");
        if (filename != null) {
            PropertyConfigurator.configure(prefix + filename);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String page = null;
        Command command = CommandFactory.getCommand(req, resp);

        try {
            page = command.execute(req, resp);
        } catch (NullPointerException ex) {
            Logger logger = Logger.getLogger(Controller.class);
            logger.error("unknown command : ", ex);
            page = PageManager.LOGIN_PAGE;
        }

        RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);

    }
}
