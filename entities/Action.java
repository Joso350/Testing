package dwolf.off_course_projects.rpg.entities;

// Enums with associated values make the code more readable, and dynamic for future additions.
public enum Action {
    ATTACK(1),
    DEFEND(2),
    CAST_SPELL(3);

    private final int number;

    Action(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Action fromInt(int i) {
        for (Action action : Action.values()) {
            if (action.getNumber() == i) {
                return action;
            }
        }
        return null;
    }
}
