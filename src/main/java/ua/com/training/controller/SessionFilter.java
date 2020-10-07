package ua.com.training.controller;

import ua.com.training.dao.entity.Roles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SessionFilter implements Filter {

    private static Map<Roles, List<String>> roleRights;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        roleRights = new HashMap<>();
        // todo implement actions from actions holder
        roleRights.put(Roles.SENIOR_CASHIER, Arrays.asList("/seniorCashier.jsp"));
        // todo implement actions from actions holder
        roleRights.put(Roles.CASHIER, Arrays.asList("" +
                "/cashier.jsp", "/cashierCart.jsp", "/check.jsp", "" +
                "/open", "/cart", "/close","/closeCheck",  "/change"));
        // todo implement actions to map from actions holder and separate user jsp
        roleRights.put(Roles.COMMODITY_EXPERT, Arrays.asList("" +
                "/commodityExpertAllProducts.jsp", "/commodityExpertChangeProductQuantity.jsp", "" +
                "/commodityExpertAddProduct.jsp", "" +
                "/view", "/change", "/add", "/insert", "/update"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");

        //todo implement localization


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setAttribute("roles", EnumSet.allOf(Roles.class));

        if (isAccess(request)) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private boolean isAccess(HttpServletRequest request) {
        String action = request.getRequestURI().substring(request.getContextPath().length());
        HttpSession session = request.getSession(false);

        // todo implement back button on browser
        // todo implement check session on existing registered user
        if (action.equals("/login.jsp") || action.equals("/error.jsp")) {
            return true;
        }

        if (session == null) {
            return false;
        }

        if (action.equals("/login") || action.equals("/logout")) {
            return true;
        }

        if (action == null || action.equals("/")) {
            return false;
        }

        Roles role = (Roles) session.getAttribute("ROLE");
        if (role == null) {
            return false;
        }

        if (action.equals("/mainUser.jsp")) {
            return true;
        }

        return roleRights.get(role).contains(action);
    }

    @Override
    public void destroy() {
    }
}
