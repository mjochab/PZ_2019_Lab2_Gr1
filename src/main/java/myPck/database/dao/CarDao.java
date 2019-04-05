package myPck.database.dao;

import myPck.database.models.Car;
import myPck.database.utils.SessionManager;

import java.util.List;

public class CarDao extends SessionManager implements Dao<Car, String> {

    public CarDao() {
    }

    @Override
    public Car findById(String id) {
        Car car = (Car) getCurrentSession().get(Car.class, id);
        return car;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Car> findAll() {
        List<Car> cars = (List<Car>) getCurrentSession().createQuery("from Car").list();
        return cars;
    }

    @Override
    public void delete(Car car) {
        getCurrentSession().delete(car);
    }

    @Override
    public void persist(Car entity) {
        getCurrentSession().save(entity);
        return null;
    }

    @Override
    public void update(Car entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void deleteAll() {
        List<Car> entityList = findAll();
        for (Car entity : entityList) {
            delete(entity);
        }
    }
}
