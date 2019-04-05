package myPck.services;

import myPck.database.dao.ServicePartDao;
import myPck.database.models.ServicePart;

import java.util.List;

public class ServicePathService {
    private ServicePartDao servicePartDao;

    public ServicePathService () {
        servicePartDao = new ServicePartDao();
    }

    public List<ServicePart> findAll () {
        servicePartDao.openCurrentSession();
        List<ServicePart> serviceParts = servicePartDao.findAll();
        servicePartDao.closeCurrentSession();
            return serviceParts;
    }

    public void persist (ServicePart entity) {
        servicePartDao.openCurrentSessionwithTransaction();
        servicePartDao.persist(entity);
        servicePartDao.closeCurrentSessionwithTransaction();
    }

    public void update (ServicePart entity){
        servicePartDao.openCurrentSessionwithTransaction();
        servicePartDao.update(entity);
        servicePartDao.closeCurrentSessionwithTransaction();
    }

    public ServicePart findById (String id) {
        servicePartDao.openCurrentSession();
        ServicePart servicePart = servicePartDao.findById(id);
        servicePartDao.closeCurrentSession();
        return servicePart;
    }

    public void delete (String id) {
        servicePartDao.openCurrentSessionwithTransaction();
        ServicePart servicePart = servicePartDao.findById(id);
        servicePartDao.delete(servicePart);
        servicePartDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll () {
        servicePartDao.openCurrentSessionwithTransaction();
        servicePartDao.deleteAll();
        servicePartDao.closeCurrentSessionwithTransaction();
    }
}
