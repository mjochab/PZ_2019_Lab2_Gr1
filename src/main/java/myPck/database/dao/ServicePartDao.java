package myPck.database.dao;

import myPck.database.models.ServicePart;
import myPck.database.utils.SessionManager;

import java.util.List;

public class ServicePartDao extends SessionManager implements Dao<ServicePart> {
    public ServicePartDao(){

    }

    @Override
    public void persist(ServicePart entity) {
     getCurrentSession().save(entity);
    }

    @Override
    public void update(ServicePart entity) {
    getCurrentSession().update(entity);
    }

    @Override
    public ServicePart findById(long id) {
        ServicePart servicePart = (ServicePart) getCurrentSession().get(ServicePart.class,id);
        return servicePart;
    }

    @Override
    public void delete(ServicePart servicePart) {
    getCurrentSession().delete(servicePart);
    }

    @Override
    public List<ServicePart> findAll() {
      List<ServicePart> serviceParts = (List<ServicePart>) getCurrentSession().createQuery("from ServicePath").list();
        return serviceParts;
    }

    @Override
    public void deleteAll() {
     List<ServicePart> entityList = findAll();
     for (ServicePart entity : entityList) {
         delete(entity);
     }
    }
}
