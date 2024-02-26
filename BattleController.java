package dwolf.off_course_projects.rpg;

import dwolf.off_course_projects.rpg.entities.*;

import java.util.*;

public class BattleController
{
    EntityFactory factory = new EntityFactory();
    List<Entity> allyTeam = new ArrayList<>(3);
    List<Entity> enemyTeam = new ArrayList<>(5);
    Scanner scanner = new Scanner(System.in);
    boolean firstBattle = true;
    public void prepareBattle()
    {
        entitySpawn();
        preBattle();
    }

    // This method allows a later implementation for a more individual character creation
    private void entitySpawn()
    {
        allyTeam.add(factory.createNewPlayer("Xanlator"));
        enemyTeam.add(factory.createMudHopper(1));
        enemyTeam.add(factory.createMudHopper(3));
        enemyTeam.add(factory.createMudHopper(6));
    }

    // This is the basis method where the game starts and to which the game may return after an action.
    private void preBattle()
    {
        if (firstBattle) {
            System.out.println("The battle begins!\n");
            firstBattle = false;
        }

        battle(askForAction());
        battleCheck(true);
        battleCheck(false);
    }

    //Initialize player actions and put them in a map
    private HashMap<Entity, HashMap<Action, Entity>> askForAction()
    {
        HashMap<Entity, HashMap<Action, Entity>> actionMap = new HashMap<>();

        for (Entity ally : allyTeam) {
            actionMap.put(ally, chooseAction(ally));
        }
        return actionMap;
    }

    //Does battle based on a built hashmap after everyone on one team has chosen an action
    private void battle(HashMap<Entity, HashMap<Action, Entity>> actionMap)
    {
        int dmgDealt;
        for(Map.Entry<Entity, HashMap<Action, Entity>> entry : actionMap.entrySet()) {
            Entity subject = entry.getKey();
            for(Map.Entry<Action, Entity> actions : entry.getValue().entrySet()) {
                Entity target = actions.getValue();
                switch (actions.getKey()) {
                    case ATTACK:
                        System.out.printf("%s attacks %s\n", subject.getName(), target.getName());
                        dmgDealt = ((BattleInterface) target).takePhysicalDamage(((BattleInterface) subject).getPhysicalDamage());
                        System.out.printf("%s hits %s for " + dmgDealt + " damage\n", subject.getName(), target.getName());
                        reportTargetHealth(target);
                        break;
                    case CAST_SPELL:
                        System.out.printf("%s hits %s with a spell.\n", subject.getName(), target.getName());
                        dmgDealt = ((BattleInterface) target).takeMagicDamage(((BattleInterface) subject).getMagicDamage());
                        System.out.printf("%s hits %s for " + dmgDealt + " damage\n", subject.getName(), target.getName());
                        reportTargetHealth(target);
                        break;
                    case DEFEND:
                        System.out.printf("%s defends.\n", target.getName());
                        int returnedHp = ((BattleInterface) target).defend();
                        System.out.printf("%s regains footing during defending and heals for %d points.\n", target.getName(), returnedHp);
                        reportTargetHealth(target);
                        break;
                }
            }
        }
    }

    //Check for health after attack
    private void reportTargetHealth(Entity target)
    {
        if (target.getHealthPoints() <= 0) {
            System.out.printf("%s dies!\n\n", target.getName());
        } else  {
            System.out.printf("%s is left with " + target.getHealthPoints() + " health points\n\n", target.getName());
        }
    }

    //Player chosen actions
    private HashMap<Action, Entity> chooseAction(Entity subject)
    {
        HashMap<Action, Entity> actionMap = new HashMap<>();

        System.out.println("What are you going to do?");
        System.out.println("1: attack\n2: defend\n3: cast spell");
        int input = scanner.nextInt();

        Action action = Action.fromInt(input);

        switch (action) {
            case ATTACK, CAST_SPELL:
                actionMap.put(action, chooseTarget());
                break;
            case DEFEND:
                actionMap.put(action, subject);
                break;
            case null:
                System.out.printf("\nWhoops! Please only input numbers from %d to %d.\n\n",
                Action.values()[0].getNumber(),
                Action.values()[Action.values().length - 1].getNumber());
                return chooseAction(subject);
        }
        return actionMap;
    }

    //Initialize enemy actions and put them in a map
    private HashMap<Entity, HashMap<Action, Entity>> enemyAction()
    {
        HashMap<Entity, HashMap<Action, Entity>> enemyActionMap = new HashMap<>();
        for (Entity enemy : enemyTeam) {
            enemyActionMap.put(enemy, randomEnemyActions(enemy));
        }
        return enemyActionMap;
    }

    //Enemy action logic, currently it is just a random action
    private HashMap<Action, Entity> randomEnemyActions(Entity subject)
    {
        HashMap<Action, Entity> actionMap = new HashMap<>();
        Random random = new Random();
        Action action = Action.fromInt(random.nextInt(1, 4));
        switch (action) {
            case ATTACK, CAST_SPELL:
                actionMap.put(action, allyTeam.get(random.nextInt(allyTeam.size())));
                break;
            case DEFEND:
                actionMap.put(action, subject);
                break;
            case null:
                System.out.println("Something went horribly wrong!");
                System.exit(1);
                break;
        }
        return actionMap;
    }

    //Choose whom are you going to attack
    private Entity chooseTarget()
    {
        System.out.println("Who are you going to attack?");
        for (int i = 0; i < enemyTeam.size(); i++) {
            System.out.printf("%d: %s\n", i, enemyTeam.get(i).getName());
        }

        int input = scanner.nextInt();
        while (input >= enemyTeam.size()) {
            System.out.println("Please choose someone from the list!");
            for (int i = 0; i < enemyTeam.size(); i++) {
                System.out.printf("%d: %s\n", i, enemyTeam.get(i).getName());
            }
            input = scanner.nextInt();
        }
        return enemyTeam.get(input);
    }

    //Checks if battle should end and if not whose turn is now
    private void battleCheck(boolean midBattle)
    {
        int deadInTeam = 0;
        for (Entity enemy : enemyTeam) {
            if (enemy.getHealthPoints() <= 0) {
                deadInTeam++;
            }
        }
        if (deadInTeam == enemyTeam.size()) {
            System.out.println("Player team wins!");
            System.exit(0);
        }
        deadInTeam = 0;

        for (Entity ally : allyTeam) {
            if (ally.getHealthPoints() <= 0) {
                deadInTeam++;
            }
        }

        if (deadInTeam == allyTeam.size()) {
            System.out.println("Enemy team wins!");
            System.exit(0);
        }

        if (midBattle) {
            battle(enemyAction());
        } else {
            preBattle();
        }
    }
}
