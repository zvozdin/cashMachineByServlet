package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.Action;
import ua.com.training.utility.SessionProductsAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        SessionProductsAttribute.setSessionAttributeProductsActualData(request);
        return "commodityExpertAllProducts.jsp";
    }
}
