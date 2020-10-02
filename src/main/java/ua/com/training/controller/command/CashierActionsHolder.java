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
        actions.put("/closeCheckCashier", new Close());
        actions.put("/close", null); // move to jsp with order or if not exist order message not
    }

    public static Set<String> getActions() {
        return actions.keySet();
    }

    public static Map<String, Action> getMap() {
        return actions;
    }
}