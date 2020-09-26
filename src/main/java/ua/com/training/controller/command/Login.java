package ua.com.training.controller.command;

import ua.com.training.service.Service;
import ua.com.training.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login implements Action {

    public static final String REGISTRATION_FIELDS_NOT_CORRECT = "Login or Password aren't correct";
    private Service service = new ServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        if (service.existUserByRoleAndLogin(role, login, password)) {
            switch (role) {
                case "SENIOR_CASHIER":
                    response.sendRedirect("seniorCashier.jsp");
                    return;
                case "CASHIER":
                    response.sendRedirect("cashier.jsp");
                    return;
                case "COMMODITY_EXPERT":
                    response.sendRedirect("commodityExpert.jsp");
                    return;
            }
        } else {
//            session.removeAttribute("role");
            request.setAttribute("error", REGISTRATION_FIELDS_NOT_CORRECT);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}