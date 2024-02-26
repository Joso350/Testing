package dwolf.off_course_projects.rpg.entities;

public class EntityFactory
{
    // Here you could have all sorts of prebuilt functions which you could call to make a
    // concrete entity without needing to write down all of their stats all the time,
    // and you'd have everything in one place.
    // You could have player classes predefined here createNewPlayerKnight(), createNewPlayerSorcerer() and so forth.
    public Monster createCustomMonster(
            String name,
            int healthPoints,
            int manaPoints,
            int level,
            int experiencePoints,
            int strength,
            int dexterity,
            int intelligence
    )
    {
        Monster monster = new Monster();
        monster.setName(name);
        monster.setHealthPoints(healthPoints);
        monster.setManaPoints(manaPoints);
        monster.setLevel(level);
        monster.setExperiencePoints(experiencePoints);
        monster.setStrength(strength);
        monster.setDexterity(dexterity);
        monster.setIntelligence(intelligence);

        return monster;
    }

    public Monster createMudHopper(int level)
    {
        Monster monster = new Monster();
        monster.setName("Mud Hopper");
        monster.setHealthPoints(50 * level);
        monster.setManaPoints(7 * level);
        monster.setLevel(level);
        monster.setExperiencePoints(10 * level);
        monster.setStrength(10 * level);
        monster.setDexterity(7 * level);
        monster.setIntelligence(10 * level);

        return monster;
    }

    public Player createNewPlayer(String name)
    {
        Player player = new Player();
        player.setName(name);
        player.setHealthPoints(200);
        player.setManaPoints(75);
        player.setLevel(1);
        player.setExperiencePoints(0);
        player.setStrength(15);
        player.setDexterity(9);
        player.setIntelligence(5);

        return player;
    }


}
