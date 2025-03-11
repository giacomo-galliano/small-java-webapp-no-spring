package com.giacomogalliano.service;

import com.giacomogalliano.model.Invoice;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    List<Invoice> invoiceList = new CopyOnWriteArrayList<>();

    public Invoice create(String userId, Integer amount) {
        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoiceList.add(invoice);
        return invoice;
    }

    public List<Invoice> findAll() {
        return invoiceList;
    }
}
