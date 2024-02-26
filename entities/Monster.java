package dwolf.off_course_projects.rpg.entities;

public class Monster extends Entity
{
    //Each monsters concrete implementation of battle logic
    //The logic here is dreadful, and we should implement some meaningful logic so the numbers and skills make sense
    //Take care of negative numbers, so you don't get healed by attack if your skill is too high

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
