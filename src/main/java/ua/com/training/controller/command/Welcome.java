package ua.com.training.controller.command;

import ua.com.training.dao.Roles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class Welcome implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute("roles", EnumSet.allOf(Roles.class));
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }
}