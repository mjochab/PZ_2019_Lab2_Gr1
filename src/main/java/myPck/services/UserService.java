package myPck.services;

import myPck.database.dao.UserDao;
import myPck.database.models.User;

import java.util.List;

public class UserService {

    /** @var UserDao **/
    private UserDao userDao;

    /**
     * Konstruktor klasy UserService i inicjalizacja UserDao
     */
    public UserService(){
        userDao = new UserDao();
    }

    /**
     * Metoda pobiera wszystkich użytkowników z bazy danych.
     * @return List<User>
     */
    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.closeCurrentSession();

        return users;
    }

    /**
     * Metoda zapisuje użytkownika do bazy danych.
     * @param entity
     */
    public void persist(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.persist(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda aktualizuje dane o użytkowniku w bazie danych.
     * @param User entity
     */
    public void update(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda znajduje użytkownika o podanym id.
     * @param long id
     * @return User
     */
    public User findById(long id) {
        userDao.openCurrentSession();
        User user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }

    /**
     * Metoda usuwa z bazy danych użytkownika o podanym id.
     * @param long id
     */
    public void delete(long id) {
        userDao.openCurrentSessionwithTransaction();
        User user = userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda usuwa wszystkich użytkowników z bazy danych.
     */
    public void deleteAll() {
        userDao.openCurrentSessionwithTransaction();
        userDao.deleteAll();
        userDao.closeCurrentSessionwithTransaction();
    }
}
