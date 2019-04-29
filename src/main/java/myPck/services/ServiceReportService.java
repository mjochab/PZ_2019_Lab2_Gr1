package myPck.services;

import myPck.database.dao.ServiceReportDao;
import myPck.database.models.ServiceReport;

import java.util.List;


    public class ServiceReportService {
        private ServiceReportDao serviceReportDao;

        public ServiceReportService() {
             serviceReportDao = new ServiceReportDao();
        }

        public List<ServiceReport> findAll () {
            serviceReportDao.openCurrentSession();
            List<ServiceReport> serviceReports = serviceReportDao.findAll();
            serviceReportDao.closeCurrentSession();
            return serviceReports;
        }

        public void persist (ServiceReport entity) {
            serviceReportDao.openCurrentSessionwithTransaction();
            serviceReportDao.persist(entity);
            serviceReportDao.closeCurrentSessionwithTransaction();
        }

        public void update (ServiceReport entity){
            serviceReportDao.openCurrentSessionwithTransaction();
            serviceReportDao.update(entity);
            serviceReportDao.closeCurrentSessionwithTransaction();
        }

        public ServiceReport findById (long id) {
            serviceReportDao.openCurrentSession();
            ServiceReport serviceReport = serviceReportDao.findById(id);
            serviceReportDao.closeCurrentSession();
            return serviceReport;
        }

        public void delete (long id) {
            serviceReportDao.openCurrentSessionwithTransaction();
            ServiceReport serviceReport = serviceReportDao.findById(id);
            serviceReportDao.delete(serviceReport);
            serviceReportDao.closeCurrentSessionwithTransaction();
        }

        public void deleteAll () {
            serviceReportDao.openCurrentSessionwithTransaction();
            serviceReportDao.deleteAll();
            serviceReportDao.closeCurrentSessionwithTransaction();
        }
    }


