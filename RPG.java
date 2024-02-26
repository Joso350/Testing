package dwolf.off_course_projects.rpg;

public class RPG {
    //I'm not sure how it is done in Java, but I'd personally reserve this part of code for special actions which
    //control the aspect of a program
    public static void main(String[] args) {
        BattleController battleController = new BattleController();
        battleController.prepareBattle();
    }
}