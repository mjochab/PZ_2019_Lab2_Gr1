package myPck.database.dao;

import myPck.database.models.Service;
import myPck.database.utils.SessionManager;

import java.util.List;

public class ServiceDao extends SessionManager implements Dao<Service> {

    public ServiceDao() {
    }

    @Override
    public Service findById(long id) {
        Service service = (Service) getCurrentSession().get(Service.class, id);
        return service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Service> findAll() {
        List<Service> services = (List<Service>) getCurrentSession().createQuery("from Service").list();
        return services;
    }

    @Override
    public void delete(Service service) {
        getCurrentSession().delete(service);
    }

    @Override
    public void persist(Service entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Service entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void deleteAll() {
        List<Service> entityList = findAll();
        for (Service entity : entityList) {
            delete(entity);
        }
    }
}
