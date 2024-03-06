package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
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
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;

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

        paymentDataVoucher.put("voucherCode", "ESHOP-12345678");
        paymentDataTransfer.put("bankName", "Jank Bago");
        paymentDataTransfer.put("referenceCode", "1234567890");

        Payment payment1 = new Payment(order1.getId(), PaymentMethods.BANKTRANSFER.getValue(),  paymentDataTransfer);
        payments.add(payment1);
        Payment payment2 = new Payment(order2.getId(), PaymentMethods.VOUCHER.getValue(), paymentDataVoucher);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        Order order = orders.get(1); // Mock Order object
        doReturn(payment).when(paymentRepository).add(any(Payment.class));

        // Call the method under test
        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());

        // Verify that paymentRepository.add() was called once with the correct parameters
        verify(paymentRepository, times(1)).add(any(Payment.class));

        // Assert that the returned payment object is the same as the one returned by the repository
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testGetPaymentByIdIfFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPaymentById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        verify(paymentRepository, times(1)).getPaymentById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentByIdIfNotFound() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");
        Payment payment = new Payment("zczc", PaymentMethods.VOUCHER.getValue(), paymentData);
        doThrow(NoSuchElementException.class).when(paymentRepository).getPaymentById(payment.getId());

        assertThrows(NoSuchElementException.class, () -> paymentService.getPayment(payment.getId()));
        verify(paymentRepository, times(1)).getPaymentById(payment.getId());
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments.size(), results.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).getAllPayments();
        assertEquals(0, result.size());
    }

}
