package ua.com.training.controller;

import ua.com.training.dao.Roles;
import ua.com.training.service.Service;
import ua.com.training.service.ServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class SessionFilter implements Filter {

    private String contextPath;
    private Service service;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        contextPath = fc.getServletContext().getContextPath();
        service = new ServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setAttribute("roles", EnumSet.allOf(Roles.class));
        chain.doFilter(req, res);

//        if (accessAllowed(request)) {
//            chain.doFilter(request, response);
//        } else {
//            String error = "You do not have permission to access the requested resource";
//            request.setAttribute("error", error);
//            request.getRequestDispatcher("error.jsp")
//                    .forward(request, response);
//        }


    }


//         else if (session != null) {
//            session.setAttribute("cashiers", service.findAllCashiersLogin());
//            session.setAttribute("commodityExperts", service.findAllCommodityExpertsLogin());
//        } else if (session.getAttribute("LOGIN_USER") == null) {
//            request.getRequestDispatcher("login.jsp?role=" + request.getParameter("role")).forward(request, response);
//        } else {
//            chain.doFilter(req, res); // Logged-in user found, so just continue request.
//        }
//    }

//    private boolean accessAllowed(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return false;
//        }
//        return false;
//    }

    @Override
    public void destroy() {
    }
}