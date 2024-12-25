import Entities.MageEntity;
import Entities.WarriorEntity;
import EtityDTOs.Gamer;
import EtityDTOs.Mage;
import EtityDTOs.Warrior;
import serviceDb.EntitiesDbService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Инициализация EntityManager для работы с базой данных
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("game_persistence_unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntitiesDbService entitiesDbService = new EntitiesDbService();

        // Сканнер для ввода данных из консоли
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите опцию: ");
        System.out.println("1. Начать новую игру");
        System.out.println("2. Продолжить игру");
        int choice = scanner.nextInt();

        Warrior warrior = null;
        Mage mage = null;

        // Выбор действия: новая игра или загрузка данных
        if (choice == 1) {
            warrior = new Warrior("Aragorn");
            mage = new Mage("Gandalf");
        } else if (choice == 2) {
            System.out.println("загружаю персонажей");
            mage = new Mage(entitiesDbService.loadPlayer(entityManager, MageEntity.class, "Gandalf"));
            warrior = new Warrior(entitiesDbService.loadPlayer(entityManager, WarriorEntity.class, "Aragorn"));

            System.out.println("Текущие данные персонажей: ");
            System.out.println(mage);
            System.out.println(warrior);
            System.out.println();

            if (mage.getHealthPower() < 0 || warrior.getHealthPower() < 0) {
                if (mage.getHealthPower() < 0) {
                    mage.setHealthPower(givePotion());
                    System.out.println("Здоровье мага было восстановлено\n");
                } else {
                    warrior.setHealthPower(givePotion());
                    System.out.println("Здоровье война было восстановлено\n");
                }
            }

        }

        // Запуск игры
        startGame(mage, warrior);

        // Сохранение данных игроков в базу
        persistPlayers(entityManager, warrior, mage);

        // Закрытие EntityManager
        entityManager.close();
        entityManagerFactory.close();
    }

    private static int givePotion() {
        int newHealth = 255 + new Random().nextInt(16);
        return newHealth;
    }


    /**
     * Логика игры: сражение между персонажами.
     */
    private static void startGame(Mage mage, Warrior warrior) throws InterruptedException {
        System.out.println("Игра начинается!\n");

        while (mage.getHealthPower() > 0 && warrior.getHealthPower() > 0) {
            // Атака мага
            mage.attack(warrior);
            mage.addExperience(10); // Добавление опыта магу
            Thread.sleep(2000); // Задержка для реалистичности
            if (warrior.getHealthPower() <= 0) {
                System.out.printf("%s победил %s!\n", mage.getName(), warrior.getName());
                break;
            }

            // Атака воина
            warrior.attack(mage);
            warrior.addExperience(10); // Добавление опыта воину
            Thread.sleep(2000);
            if (mage.getHealthPower() <= 0) {
                System.out.printf("%s победил %s!\n", warrior.getName(), mage.getName());
                break;
            }
        }
    }

    /**
     * Сохранение игроков в базу данных.
     */
    public static void persistPlayers(EntityManager em, Warrior warrior, Mage mage) {
        em.getTransaction().begin();
        em.merge(warrior.createEntity(warrior));
        em.merge(mage.createEntity(mage));
        em.getTransaction().commit();
        System.out.println("Персонажи успешно сохранены в базу данных.");
    }
}
