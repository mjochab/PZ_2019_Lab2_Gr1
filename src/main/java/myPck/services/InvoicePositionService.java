package myPck.services;

import myPck.database.dao.InvoicePositionDao;
import myPck.database.models.InvoicePosition;

import java.util.List;

public class InvoicePositionService {

    private InvoicePositionDao invoicePositionDao;

     public InvoicePositionService(){
         invoicePositionDao = new InvoicePositionDao();
     }
     public List<InvoicePosition> findAll () {
     invoicePositionDao.openCurrentSession();
     List<InvoicePosition> invoicePositions = invoicePositionDao.findAll();
     invoicePositionDao.closeCurrentSession();
         return invoicePositions;
     }
     public void persist (InvoicePosition entity){
         invoicePositionDao.openCurrentSessionwithTransaction();
         invoicePositionDao.persist(entity);
         invoicePositionDao.closeCurrentSessionwithTransaction();
     }
     public void update (InvoicePosition entity) {
         invoicePositionDao.openCurrentSessionwithTransaction();
         invoicePositionDao.update(entity);
         invoicePositionDao.closeCurrentSessionwithTransaction();
     }
     public InvoicePosition findById (String id){
         invoicePositionDao.openCurrentSession();
         InvoicePosition invoicePosition = invoicePositionDao.findById(id);
         invoicePositionDao.closeCurrentSession();
         return invoicePosition;
     }
     public void delete(String id) {
         invoicePositionDao.openCurrentSessionwithTransaction();
         InvoicePosition invoicePosition = invoicePositionDao.findById(id);
         invoicePositionDao.delete(invoicePosition);
         invoicePositionDao.closeCurrentSessionwithTransaction();
     }
     public void deleteAll () {
         invoicePositionDao.openCurrentSessionwithTransaction();
         invoicePositionDao.deleteAll();
         invoicePositionDao.closeCurrentSessionwithTransaction();
     }
}


