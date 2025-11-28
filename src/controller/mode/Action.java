package controller.mode;

import java.util.EnumMap;
import java.util.Map;

import renderer.ui.Toolable;

public enum Action implements Toolable {
    PROJECTION, // true - perspective, false - orthogonal
    CLIPPING, // true - full clipping, false - trivial clipping
    POINT_SELECTION;

    private static Map<Action, Boolean> actionStates = new EnumMap<>(Action.class);

    static {
        for (Action a : Action.values()) {
            actionStates.put(a, true);
        }
        POINT_SELECTION.setOff();
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
