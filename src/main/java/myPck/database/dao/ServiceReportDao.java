package myPck.database.dao;

import myPck.database.models.ServiceReport;
import myPck.database.utils.SessionManager;

import java.util.List;

public class ServiceReportDao extends SessionManager implements Dao<ServiceReport> {
    public ServiceReportDao() {

    }

    @Override
    public void persist(ServiceReport entity) {
     getCurrentSession().save(entity);
    }

    @Override
    public void update(ServiceReport entity) {
     getCurrentSession().update(entity);
    }

    @Override
    public ServiceReport findById(long id) {
        ServiceReport serviceReport = (ServiceReport) getCurrentSession().get(ServiceReport.class,id);
        return serviceReport;
    }

    @Override
    public void delete(ServiceReport serviceReport) {
    getCurrentSession().delete(serviceReport);
    }

    @Override
    public List<ServiceReport> findAll() {
        List<ServiceReport> serviceReports = (List<ServiceReport>) getCurrentSession().createQuery("from ServiceReport").list();
        return serviceReports;
    }

    @Override
    public void deleteAll() {
     List<ServiceReport> entityList = findAll();
     for (ServiceReport entity : entityList) {
         delete(entity);
     }
    }
}
