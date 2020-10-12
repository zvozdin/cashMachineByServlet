package ua.com.training.controller.command.senior;

import ua.com.training.controller.command.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SeniorCashierActionsHolder {

    private static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("/cancel%20order", new Checks());
        actions.put("/deleteOrder", new DeleteOrder());
//        actions.put("/change", new Change());
//        actions.put("/closeCheck", new Close());
//        actions.put("/close", new Cart());
    }

    public static Set<String> getActions() {
        return actions.keySet();
    }

    public static Map<String, Action> getMap() {
        return actions;
    }
}
