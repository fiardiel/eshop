package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, HashMap<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        paymentRepository.add(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String id) {
        return paymentRepository.getPaymentById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.add(payment);
        orderRepository.save(orderRepository.findById(payment.getId()));
        return payment;
    }
}
