package controller.mode;

import java.util.EnumMap;
import java.util.Map;

public enum Action {
    CHANGE_PROJECTION,
    CHANGE_CLIPPING;

    private static Map<Action, Boolean> actionStates = new EnumMap<>(Action.class);

    static {
        for (Action a : Action.values()) {
            actionStates.put(a, false);
        }
    }

    public boolean isOn() {
        return actionStates.get(this);
    }

    public void setOn() {
        actionStates.put(this, true);
    }

    public void setOff() {
        actionStates.put(this, false);
    }

    public void toggle() {
        if(actionStates.get(this)) {
            actionStates.put(this, false);
            return;
        }
        actionStates.put(this, true);
    }
}
