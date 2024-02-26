package dwolf.off_course_projects.rpg.entities;

import dwolf.off_course_projects.rpg.BattleInterface;

public class Entity implements BattleInterface
{
    //Base code for every entity with getters and setters
    protected String name;
    protected int healthPoints = 10;
    protected int manaPoints = 0;
    protected int level = 1;
    protected int experiencePoints = 1;
    protected int strength = 1;
    protected int dexterity = 1;
    protected int intelligence = 1;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealthPoints()
    {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public int getManaPoints()
    {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints)
    {
        this.manaPoints = manaPoints;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getExperiencePoints()
    {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints)
    {
        this.experiencePoints = experiencePoints;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getDexterity()
    {
        return dexterity;
    }

    public void setDexterity(int dexterity)
    {
        this.dexterity = dexterity;
    }

    public int getIntelligence()
    {
        return intelligence;
    }

    public void setIntelligence(int intelligence)
    {
        this.intelligence = intelligence;
    }

    @Override
    public int getPhysicalDamage()
    {
        return 0;
    }

    @Override
    public int getMagicDamage()
    {
        return 0;
    }

    @Override
    public int takePhysicalDamage(int damage)
    {
        return 0;
    }

    @Override
    public int takeMagicDamage(int damage)
    {
        return 0;
    }

    @Override
    public int defend()
    {
        return 0;
    }
}
