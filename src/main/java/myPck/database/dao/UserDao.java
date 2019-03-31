package myPck.database.dao;

import myPck.database.models.User;
import myPck.database.utils.SessionManager;

import java.util.List;

public class UserDao extends SessionManager implements Dao<User, String> {

    public UserDao() {
    }

    @Override
    public User findById(String id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
        List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
        return users;
    }

    @Override
    public void delete(User user) {
        getCurrentSession().delete(user);
    }

    @Override
    public void persist(User entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }
}