package id.ac.ui.cs.advprog.eshop.model;


import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    @Test
    void testCreatePaymentEmptyPaymentData() {
        HashMap<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Bank Transfer", "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "SUCCESS", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("Voucher Code", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreateStatusPaymentRejectedStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "REJECTED", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("Voucher Code", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "MATAMU", paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "MATAMU", "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidPaymentData() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("gigimu", "hatimu");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "SUCCESS", paymentData);
        });
    }

    @Test
    void testSetStatusToRejected() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "SUCCESS", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "SUCCESS", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "Voucher Code", "REJECTED", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
}