package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.HashMap;
import java.util.List;

public interface PaymentService {
    public void addPayment(Order order, String method, HashMap<String, String> paymentData);
    public void updatePaymentStatus(String id, String status);
    public Payment getPayment(String id);
    public List<Payment> getAllPayments();
}
