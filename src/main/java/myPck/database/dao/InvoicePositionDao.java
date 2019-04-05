package myPck.database.dao;

import myPck.database.models.InvoicePosition;
import myPck.database.utils.SessionManager;

import java.util.List;

public class InvoicePositionDao extends SessionManager implements Dao<InvoicePosition,String> {
    public InvoicePositionDao(){

    }

    @Override
    public void persist(InvoicePosition entity) {
    getCurrentSession().save(entity);
    }

    @Override
    public void update(InvoicePosition entity) {
    getCurrentSession().update(entity);
    }

    @Override
    public InvoicePosition findById(String id) {
        InvoicePosition invoicePosition = (InvoicePosition) getCurrentSession().get(InvoicePosition.class,id);
        return invoicePosition;
    }

    @Override
    public void delete(InvoicePosition invoicePosition) {
    getCurrentSession().delete(invoicePosition);
    }

    @Override
    public List<InvoicePosition> findAll() {
        List<InvoicePosition> invoicePositions = (List<InvoicePosition>) getCurrentSession().createQuery("from InvoicePosition").list();
        return null;
    }

    @Override
    public void deleteAll() {
     List<InvoicePosition> entityList = findAll();
     for (InvoicePosition entity : entityList) {
         delete(entity);
     }
    }
}
