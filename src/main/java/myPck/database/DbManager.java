package myPck.database;


import myPck.database.models.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbManager {

    public static EntityManager entityManager;

    public static void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

        entityManager = entityManagerFactory.createEntityManager();
        
    }
    public static void addSampleDataTest(){
        Car car = new Car("testModel", "testBrand", "testType", "2000");

        entityManager.getTransaction().begin();
        entityManager.merge(car);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
