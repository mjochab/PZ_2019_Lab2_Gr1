package myPck.database;


import myPck.database.models.Car;
import myPck.database.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbManager {

    public static EntityManager entityManager;
    public static EntityManagerFactory entityManagerFactory;

    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MyConnect");
        entityManager = entityManagerFactory.createEntityManager();
    }
    public static void addSampleDataTest(){
        Car car = new Car("testModel", "testBrand", "testType", "2000");
        User user = new User("email@o2.pl", "Jan", "Kowalski", "login", "password", "A");

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
