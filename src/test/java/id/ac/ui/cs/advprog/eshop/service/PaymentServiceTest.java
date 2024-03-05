package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    List<Order> orders = new ArrayList<>();

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName ("Sampo Cap Bambang"); product1.setProductQuantity(2);
        products.add(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order ("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        HashMap<String, String> paymentDataVoucher = new HashMap<>();
        HashMap<String, String> paymentDataTransfer = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP-123");
        paymentDataTransfer.put("bankName", "Jank Bago");
        paymentDataTransfer.put("referenceCode", "1234567890");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Bank Transfer",  paymentDataTransfer);
        payments.add(payment1);
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078", "Voucher Code", paymentDataVoucher);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).add(payment);

        Order order = orders.get(1);
        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).add(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfExists() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPaymentById(payment.getId());

        Order order = orders.get(1);
        assertNull(paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData()));
        verify(paymentRepository, times(0)).add(payment);
    }

    @Test
    void testGetPaymentByIdIfFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPaymentById(payment.getId());

        Payment result = paymentService.getPaymentById(payment.getId());
        verify(paymentRepository, times(1)).getPaymentById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentByIdIfNotFound() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");
        Payment payment = new Payment("zczc", "Voucher Code", paymentData);
        doThrow(NoSuchElementException.class).when(paymentRepository).getPaymentById(payment.getId());

        assertThrows(NoSuchElementException.class, () -> paymentService.getPayment(payment.getId()));
        verify(paymentRepository, times(1)).getPaymentById(payment.getId());
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.add(payment);
        }

        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).getAllPayments();
        assertEquals(payments.size(), result.size());
    }
}
