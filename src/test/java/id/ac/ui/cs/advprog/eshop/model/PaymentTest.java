package id.ac.ui.cs.advprog.eshop.model;


import enums.PaymentMethods;
import enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testCreate() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethods.VOUCHER.getValue(), paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }
    @Test
    void testCreatePaymentEmptyPaymentData() {
        HashMap<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethods.BANKTRANSFER.getValue(), paymentData);
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

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethods.VOUCHER.getValue(), paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethods.VOUCHER.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP-123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethods.VOUCHER.getValue(), paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals("SUCCESS", payment.getStatus());
    }
}