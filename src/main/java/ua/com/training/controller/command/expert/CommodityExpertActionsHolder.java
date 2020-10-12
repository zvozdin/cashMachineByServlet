package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.*;
import ua.com.training.controller.command.common.Change;

import java.util.*;

public class CommodityExpertActionsHolder {

    private static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("/view", new View());
        actions.put("/add", new Add());
        actions.put("/change", new Change());
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
