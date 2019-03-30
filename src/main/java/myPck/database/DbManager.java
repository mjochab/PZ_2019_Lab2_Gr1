package myPck.database;


import myPck.database.models.Car;

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

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
