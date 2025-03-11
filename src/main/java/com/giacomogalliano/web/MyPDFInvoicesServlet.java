package com.giacomogalliano.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giacomogalliano.model.Invoice;
import com.giacomogalliano.service.InvoiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MyPDFInvoicesServlet extends HttpServlet {

    private InvoiceService invoiceService = new InvoiceService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getRequestURI().equalsIgnoreCase("/")) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<html>\n" +
                    "<body>\n" +
                    "<h1> Hello, first servlet here</h1> \n" +
                    "</body>\n" +
                    "</html>"
            );
        } else if (req.getRequestURI().equalsIgnoreCase("/invoices")) {
            resp.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = invoiceService.findAll();
            resp.getWriter().print(objectMapper.writeValueAsString(invoices));
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = req.getParameter("user_id");
            Integer amount = Integer.parseInt(req.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);
            String json = objectMapper.writeValueAsString(invoice);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().print(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
