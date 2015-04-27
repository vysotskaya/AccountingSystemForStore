package servlets;

import dao.DAOFactory;

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
        String command = (String) req.getParameter("command");
        String loginInput = (String) req.getParameter("loginInput");
        if (command.equals("showallreceivers")) {
            List receivers = DAOFactory.getFactory().getReceiverDAO().read();
            req.setAttribute("list", receivers);
            req.getRequestDispatcher("/receiverspage.jsp").forward(req, resp);
        }
        if (command.equals("showallsenders")) {
            List senders = DAOFactory.getFactory().getSenderDAO().read();
            req.setAttribute("list", senders);
            req.getRequestDispatcher("/senderspage.jsp").forward(req, resp);
        }
        if (command.equals("authorizedafterlogin")) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        if (command.equals("login")) {
            List records = DAOFactory.getFactory().getRecordDAO().read();
            req.setAttribute("isAuthorized", false);
            req.setAttribute("list", records);
            req.getRequestDispatcher("/storepage.jsp").forward(req, resp);
        } else if (loginInput.equals("true")) {
            List records = DAOFactory.getFactory().getRecordDAO().read();
            req.setAttribute("isAuthorized", true);
            req.setAttribute("list", records);
            req.getRequestDispatcher("/storepage.jsp").forward(req, resp);
        } else {
            req.setAttribute("isWrong", true);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
