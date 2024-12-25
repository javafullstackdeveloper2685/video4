package EtityDTOs;

public abstract class Gamer {


    public abstract void attack(Gamer gamer);

    public abstract void doge(Gamer gamer);

    public abstract int getHealthPower();

    public abstract void setHealthPower(int healthPower);

    public abstract int getAttackPower();

    public abstract void addExperience(int i);

    public abstract int getExperience();

    public abstract int getLevel();

    public abstract String getName();

}
