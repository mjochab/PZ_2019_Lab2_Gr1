package myPck.services;

import myPck.database.dao.ServiceDao;
import myPck.database.models.Service;

import java.util.List;

public class ServiceService {
    /** @var myPck.database.models.ServiceDao **/
    private ServiceDao serviceDao;

    /**
     * Konstruktor klasy ServiceService i inicjalizacja ServiceDao
     */
    public ServiceService(){
        serviceDao = new ServiceDao();
    }

    /**
     * Metoda pobiera wszystkie serwisy z bazy danych.
     * @return List<Service>
     */
    public List<Service> findAll() {
        serviceDao.openCurrentSession();
        List<Service> services = serviceDao.findAll();
        serviceDao.closeCurrentSession();

        return services;
    }

    /**
     * Metoda zapisuje serwis do bazy danych.
     * @param entity
     */
    public void persist(Service entity) {
        serviceDao.openCurrentSessionwithTransaction();
        serviceDao.persist(entity);
        serviceDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda aktualizuje dane o serwisie w bazie danych.
     * @param Service entity
     */
    public void update(Service entity) {
        serviceDao.openCurrentSessionwithTransaction();
        serviceDao.update(entity);
        serviceDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda znajduje serwis o podanym id.
     * @param long id
     * @return Service
     */
    public Service findById(long id) {
        serviceDao.openCurrentSession();
        Service service = serviceDao.findById(id);
        serviceDao.closeCurrentSession();
        return service;
    }

    /**
     * Metoda usuwa z bazy danych serwis o podanym id.
     * @param long id
     */
    public boolean delete(long id) {
        serviceDao.openCurrentSessionwithTransaction();
        Service service = serviceDao.findById(id);
        serviceDao.delete(service);
        return serviceDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda usuwa wszystkie serwisy z bazy danych.
     */
    public void deleteAll() {
        serviceDao.openCurrentSessionwithTransaction();
        serviceDao.deleteAll();
        serviceDao.closeCurrentSessionwithTransaction();
    }
}
