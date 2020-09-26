package ua.com.training.controller;

import ua.com.training.controller.command.Action;
import ua.com.training.controller.command.Login;
import ua.com.training.controller.command.Welcome;

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
        actions = new HashMap<>();
        actions.put("/", new Welcome());
        actions.put("/login", new Login());
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    // todo make logout by including jsp

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionKey = getAction(request);

        Action action = actions.get(actionKey);
        action.execute(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length());
    }
}