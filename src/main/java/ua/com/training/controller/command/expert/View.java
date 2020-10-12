package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "commodityExpertAllProducts.jsp";
    }
}
