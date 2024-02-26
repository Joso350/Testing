package dwolf.off_course_projects.rpg;

public interface BattleInterface
{
    int getPhysicalDamage();
    int getMagicDamage();
    int takePhysicalDamage(int damage);
    int takeMagicDamage(int damage);
    int defend();
}
