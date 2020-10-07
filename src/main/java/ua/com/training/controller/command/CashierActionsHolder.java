package ua.com.training.controller.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CashierActionsHolder {

    private static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("/open", new Open());
        actions.put("/cart", new Cart());
        actions.put("/change", new Change());
        actions.put("/closeCheck", new Close());
        actions.put("/close", new Cart());
    }

    public static Set<String> getActions() {
        return actions.keySet();
    }

    public static Map<String, Action> getMap() {
        return actions;
    }
}