package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.*;

import java.util.*;

public class CommodityExpertActionsHolder {

    private static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("/view", new View());
        actions.put("/add", new Add());
        actions.put("/changeExpert", new ChangeProductQuantity());
        actions.put("/update", new Update());
        actions.put("/insert", new Insert());
    }

    public static Set<String> getActions() {
        return actions.keySet();
    }

    public static Map<String, Action> getMap() {
        return actions;
    }
}
