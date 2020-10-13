package ua.com.training.controller.command.senior;

import ua.com.training.controller.command.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SeniorCashierActionsHolder {

    private static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("/cancelOrder", new Checks());
        actions.put("/deleteOrder", new DeleteOrder());
        actions.put("/cancelProduct", new Checks());
        actions.put("/deleteProduct", new DeleteProduct());
        actions.put("/makeXReport", new XReport());
    }

    public static Set<String> getActions() {
        return actions.keySet();
    }

    public static Map<String, Action> getMap() {
        return actions;
    }
}
