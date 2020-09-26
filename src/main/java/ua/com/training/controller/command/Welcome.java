package ua.com.training.controller.command;

import ua.com.training.dao.Roles;
import ua.com.training.service.Service;
import ua.com.training.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

public class Welcome implements Action {

    private Service service = new ServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roles", EnumSet.allOf(Roles.class));

        HttpSession session = request.getSession();
        // todo try with one select into db(cashiers+experts logins) and set into attributes with lambda filter
        session.setAttribute("cashiers", service.findAllCashiersLogin());
        session.setAttribute("commodityExperts", service.findAllCommodityExpertsLogin());

        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }
}