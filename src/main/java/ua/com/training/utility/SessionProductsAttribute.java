package ua.com.training.utility;

import ua.com.training.dao.ProductDao;
import ua.com.training.dao.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

public final class SessionProductsAttribute {

    private SessionProductsAttribute() {
    }

    public static void setSessionAttributeProductsActualData(HttpServletRequest request) {
        // todo implement with ProductService
        List<Product> products = new ProductDao().findAllProducts();
        request.getSession().setAttribute("products", products);
    }

    public static List<Product> getChosenProducts (HttpServletRequest request){
        List<Product> products = (List<Product>) request.getSession().getAttribute("products");
        List<Product> result = new LinkedList<>();
        for (Product product : products) {
            String parameter = request.getParameter(product.getCode());
            if (!(parameter == null || parameter.isEmpty())) {
                product.setQuantity(Integer.parseInt(parameter));
                result.add(product);
            }
        }
        return result;
    }
}
