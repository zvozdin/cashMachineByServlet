package ua.com.training.controller.command.cashier;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;
import ua.com.training.dao.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", new ArrayList<>());
        }

        Optional<Product> product = getProductsFromClient(request);
        if (product.isPresent()) {
            cart.add(product.get());
            session.setAttribute("cart", cart);
        }

        return "cashierCart.jsp";
    }

    private Optional<Product> getProductsFromClient(HttpServletRequest request) {
        List<Product> products = new StockDao().findAllProducts(0, new StockDao().getRowsCount());
        for (Product product : products) {
            String parameter = request.getParameter(product.getCode());
            if (!(parameter == null
                    || parameter.isEmpty()
                    || Integer.parseInt(parameter) == 0)) {

                return Optional.ofNullable(new Product.ProductBuilder()
                        .id(product.getId())
                        .code(product.getCode())
                        .name(product.getName())
                        .name_UA(product.getName_UA())
                        .size(product.getSize())
                        .quantity(Integer.parseInt(parameter))
                        .price(product.getPrice())
                        .bill(Integer.parseInt(parameter) * product.getPrice())
                        .build());
            }
        }

        return Optional.empty();
    }
}
