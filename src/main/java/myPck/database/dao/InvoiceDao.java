package myPck.database.dao;

import myPck.database.models.Invoice;
import myPck.database.utils.SessionManager;

import java.util.List;

public class InvoiceDao extends SessionManager implements Dao<Invoice> {
    public  InvoiceDao() {
    }

    @Override
    public void persist(Invoice entity) {
     getCurrentSession().save(entity);
    }

    @Override
    public void update(Invoice entity) {
    getCurrentSession().update(entity);
    }

    @Override
    public Invoice findById(long id) {
        Invoice invoice = (Invoice) getCurrentSession().get(Invoice.class,id);
        return invoice;
    }

    @Override
    public void delete(Invoice invoice) {
     getCurrentSession().delete(invoice);
    }

    @Override
    public List<Invoice> findAll() {
      List<Invoice> invoices = (List<Invoice>) getCurrentSession().createQuery("from Invoice").list();
        return invoices;
    }

    @Override
    public void deleteAll(){
        List<Invoice> entityList = findAll();
        for (Invoice entity : entityList) {
            delete(entity);
        }
    }
}
