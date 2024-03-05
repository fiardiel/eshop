package id.ac.ui.cs.advprog.eshop.model;


import enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testCreate() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("Voucher Code", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }
    @Test
    void testCreatePaymentEmptyPaymentData() {
        HashMap<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Bank Transfer", paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Meow", paymentData);
        });
    }

    @Test
    void testSetStatusToRejected() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "Ashiap");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
}