package ua.com.training.controller.command.common;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.entity.Roles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Change implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        switch ((Roles) session.getAttribute("ROLE")) {
            case CASHIER:
                return "cashierCart.jsp";
            case COMMODITY_EXPERT:
                return "commodityExpertChangeProductQuantity.jsp";
            default:
                return "login.jsp";
        }
    }
}
