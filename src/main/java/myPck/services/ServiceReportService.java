package myPck.services;

import myPck.database.dao.ServiceReportsDao;
import myPck.database.models.ServiceReport;

import java.util.List;


    public class ServiceReportService {
        private ServiceReportsDao serviceReportsDao;

        public ServiceReportService() {
            ServiceReportsDao servicePartDao = new ServiceReportsDao();
        }

        public List<ServiceReport> findAll () {
            serviceReportsDao.openCurrentSession();
            List<ServiceReport> serviceReports = serviceReportsDao.findAll();
            serviceReportsDao.closeCurrentSession();
            return serviceReports;
        }

        public void persist (ServiceReport entity) {
            serviceReportsDao.openCurrentSessionwithTransaction();
            serviceReportsDao.persist(entity);
            serviceReportsDao.closeCurrentSessionwithTransaction();
        }

        public void update (ServiceReport entity){
            serviceReportsDao.openCurrentSessionwithTransaction();
            serviceReportsDao.update(entity);
            serviceReportsDao.closeCurrentSessionwithTransaction();
        }

        public ServiceReport findById (long id) {
            serviceReportsDao.openCurrentSession();
            ServiceReport serviceReport = serviceReportsDao.findById(id);
            serviceReportsDao.closeCurrentSession();
            return serviceReport;
        }

        public void delete (long id) {
            serviceReportsDao.openCurrentSessionwithTransaction();
            ServiceReport serviceReport = serviceReportsDao.findById(id);
            serviceReportsDao.delete(serviceReport);
            serviceReportsDao.closeCurrentSessionwithTransaction();
        }

        public void deleteAll () {
            serviceReportsDao.openCurrentSessionwithTransaction();
            serviceReportsDao.deleteAll();
            serviceReportsDao.closeCurrentSessionwithTransaction();
        }
    }


