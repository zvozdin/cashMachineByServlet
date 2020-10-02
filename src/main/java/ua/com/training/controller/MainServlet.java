package ua.com.training.controller;

import ua.com.training.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private Map<String, Action> actions;

    public MainServlet() {
        // todo initialize in another place or class
        actions = new HashMap<>();
        actions.put("/login", new Login());
        actions.put("/logout", new Logout());
        actions.putAll(CommodityExpertActionsHolder.getMap());
        actions.putAll(CashierActionsHolder.getMap());
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String actionKey = requestURI.substring(request.getContextPath().length());
        Action action = actions.get(actionKey);
        String page = action.execute(request, response);

        response.sendRedirect(page);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
