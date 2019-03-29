package myPck.database;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbManager {

    public static EntityManager entityManager;

    public static void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

        entityManager = entityManagerFactory.createEntityManager();


    }


    public static void addPerson(){
//        Person person = new Person();
//        person.setId(10);
//        person.setName("Test");
//        person.setSurname("Test");

        entityManager.getTransaction().begin();
//        entityManager.merge(person);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
