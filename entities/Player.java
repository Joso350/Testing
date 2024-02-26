package dwolf.off_course_projects.rpg.entities;

public class Player extends Entity
{
    //Concrete implementations for player logic which may vary from monsters, that's why I didn't do it in Entity class
    //Is it better to do it with getters and setters here, I don't really know. It works either way
    //I left a comment in monster class about logic, it's bad
    @Override
    public int getPhysicalDamage()
    {
        return (int) Math.round(strength * level * 0.5);
    }

    @Override
    public int getMagicDamage()
    {
        return (int) Math.round(intelligence * level * 0.35);
    }

    @Override
    public int takePhysicalDamage(int damage)
    {
        int dmgToTake = damage * (strength / 10);
        healthPoints -= dmgToTake;
        return dmgToTake;
    }
    @Override
    public int takeMagicDamage(int damage)
    {
        int dmgToTake = damage * (intelligence / 10);
        healthPoints -= dmgToTake;
        return dmgToTake;
    }

    @Override
    public int defend()
    {
        int toHeal = dexterity / 10 + 1;
        healthPoints += toHeal;
        return toHeal;
    }
}
