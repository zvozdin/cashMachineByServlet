package ua.com.training.controller;

import ua.com.training.dao.Roles;
import ua.com.training.service.Service;
import ua.com.training.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

public class MainServlet extends HttpServlet {

    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new ServiceImpl();
    }

    // todo make logout by including jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/")) {
            request.setAttribute("roles", EnumSet.allOf(Roles.class));
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/login")) {
            HttpSession session = request.getSession();
            Object role = session.getAttribute("role");
            switch (role.toString()) {
                case "SENIOR_CASHIER":
                    // todo database. retrieve and check for null or by Optional
                    if (service.existSeniorCashierByLogin(
                            request.getParameter("login"), request.getParameter("password"))) {
//                        session.setAttribute("");
                        response.sendRedirect("seniorCashier.jsp");
                    } else {
                        session.removeAttribute("role");
                        response.setStatus(401);
                        // todo error page by mapping error
                        response.sendRedirect("general-error.jsp?error=Try Again");
                    }
                    break;
                default:
                    // do nothing
            }

            return;
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length());
    }
}