package myPck.services;

import myPck.database.dao.InvoiceDao;
import myPck.database.models.Invoice;

import java.util.List;

public class InvoiceService {

    /** @var InvoiceDao **/
    private InvoiceDao invoiceDao;

    /**
     * Konstruktor klasy InvoiceService i inicjalizacja InvoiceDao
     */
    public InvoiceService () {
        invoiceDao = new InvoiceDao();
    }

    /**
     * Metoda pobiera wszystkie samochody z bazy danych.
     * @return List<Invoice>
     */
    public List<Invoice> findAll() {
        invoiceDao.openCurrentSession();
        List<Invoice> invoices = invoiceDao.findAll();
        invoiceDao.closeCurrentSession();

        return invoices;
    }
    /**
     * Metoda zapisuje Invoice do bazy danych.
     * @param entity
     */
   public  void persist (Invoice entity){
       invoiceDao.openCurrentSessionwithTransaction();
       invoiceDao.persist(entity);
       invoiceDao.closeCurrentSessionwithTransaction();
   }
    /**

     */
    public Invoice findById(String id) {
        invoiceDao.openCurrentSession();
        Invoice invoice = invoiceDao.findById(id);
        invoiceDao.closeCurrentSession();
        return invoice;
    }

    public void delete(String id) {
        invoiceDao.openCurrentSessionwithTransaction();
        Invoice invoice = invoiceDao.findById(id);
        invoiceDao.delete(invoice);
        invoiceDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda usuwa wszystkie invoice z bazy danych.
     */
    public void deleteAll () {
        invoiceDao.openCurrentSessionwithTransaction();
        invoiceDao.deleteAll();
        invoiceDao.closeCurrentSessionwithTransaction();
    }
}
