package myPck.services;

import myPck.database.dao.CarDao;
import myPck.database.models.Car;

import java.util.List;

public class CarService {

    /** @var CarDao **/
    private CarDao carDao;

    /**
     * Konstruktor klasy CarService i inicjalizacja CarDao
     */
    public CarService(){
        carDao = new CarDao();
    }

    /**
     * Metoda pobiera wszystkie samochody z bazy danych.
     * @return List<Car>
     */
    public List<Car> findAll() {
        carDao.openCurrentSession();
        List<Car> cars = carDao.findAll();
        carDao.closeCurrentSession();

        return cars;
    }

    /**
     * Metoda zapisuje samochód do bazy danych.
     * @param entity
     */
    public void persist(Car entity) {
        carDao.openCurrentSessionwithTransaction();
        carDao.persist(entity);
        carDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda aktualizuje dane o samochodzie w bazie danych.
     * @param Car entity
     */
    public void update(Car entity) {
        carDao.openCurrentSessionwithTransaction();
        carDao.update(entity);
        carDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda znajduje samochód o podanym id.
     * @param long id
     * @return Car
     */
    public Car findById(long id) {
        carDao.openCurrentSession();
        Car car = carDao.findById(id);
        carDao.closeCurrentSession();
        return car;
    }

    /**
     * Metoda usuwa z bazy danych samochó o podanym id.
     * @param long id
     * @return
     */
    public boolean delete(long id) {
        carDao.openCurrentSessionwithTransaction();
        Car car = carDao.findById(id);
        carDao.delete(car);
        return carDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda usuwa wszystkie samochody z bazy danych.
     */
    public void deleteAll() {
        carDao.openCurrentSessionwithTransaction();
        carDao.deleteAll();
        carDao.closeCurrentSessionwithTransaction();
    }
}
