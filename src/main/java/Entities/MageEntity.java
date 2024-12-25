package Entities;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mage")
public class MageEntity {

    @Id
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid ;

    @Column (name ="exp")
    Integer experience;

    @Column (name ="lvl")
    Integer level;

    @Column (name ="name")
    String name;

    @Column (name = "health_power")
    Integer healthPower;

    @Column (name ="attack_power")
    Integer attackPower;

    // No-Args-Constructor for JPA
    public MageEntity() {
    }

    public MageEntity(UUID uuid, Integer experience, Integer level, String name, Integer healthPower, Integer attackPower) {
        this.uuid = uuid;
        this.experience = experience;
        this.level = level;
        this.name = name;
        this.healthPower = healthPower;
        this.attackPower = attackPower;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer healthPower() {
        return healthPower;
    }

    public void setHealthPower(Integer healthPower) {
        this.healthPower = healthPower;
    }

    public Integer attackPower() {
        return attackPower;
    }

    public void setAttackPower(Integer attackPower) {
        this.attackPower = attackPower;
    }
}
