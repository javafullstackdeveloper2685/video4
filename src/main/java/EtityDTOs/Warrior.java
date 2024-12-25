package EtityDTOs;

import Entities.WarriorEntity;

import java.util.Random;
import java.util.UUID;

public class Warrior extends Gamer {
    private String name;
    private int healthPower;
    private int attackPower;
    private int experience;
    private int level;
    private UUID uuid;


    public Warrior(String name) {
        this.uuid = UUID.randomUUID();
        this.healthPower = 255 + new Random().nextInt(16);
        this.attackPower = 10 + new Random().nextInt(6);
        this.name = name;
    }

    public Warrior(WarriorEntity warriorEntity) {
        this.name = warriorEntity.getName();
        this.experience = warriorEntity.getExperience();
        this.level = warriorEntity.getLevel();
        this.uuid = warriorEntity.getUuid();
        this.healthPower = warriorEntity.healthPower();
        this.attackPower = warriorEntity.attackPower();
    }

    public WarriorEntity createEntity(Warrior warrior) {
        WarriorEntity warriorEntity = new WarriorEntity();
        warriorEntity.setUuid(uuid);
        warriorEntity.setLevel(this.level);
        warriorEntity.setExperience(this.experience);
        warriorEntity.setName(name);
        warriorEntity.setHealthPower(healthPower);
        warriorEntity.setAttackPower(attackPower);
        return warriorEntity;
    }

    @Override
    public void attack(Gamer gamer) {
        int hit = this.attackPower + new Random().nextInt(50);
        gamer.setHealthPower(gamer.getHealthPower() - hit);
        System.out.printf("Warrior %s attacks the opponent and hits is with %d\n", this.name, hit);
        System.out.printf("Opponent has %d health power left\n", this.healthPower);
        System.out.println();
    }

    @Override
    public void doge(Gamer gamer) {
        System.out.printf("Gamer %s the attack", this.name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHealthPower() {
        return healthPower;
    }

    @Override
    public void setHealthPower(int healthPower) {
        this.healthPower = healthPower;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void addExperience(int i) {
        if (this.experience == 100) {
            this.attackPower = this.attackPower + 3;
            this.level++;
            this.experience = 0;
        } else {
            setExperience(this.experience + i);
        }
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + name + '\'' +
                ", healthPower=" + healthPower +
                ", attackPower=" + attackPower +
                ", experience=" + experience +
                ", level=" + level +
                ", uuid=" + uuid +
                '}';
    }
}