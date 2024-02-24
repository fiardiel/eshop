package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepositoryImpl productRepository;

    @InjectMocks
    private ProductServiceImpl service;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductName("CRV DEPOK ZJC");
        product.setProductQuantity(100);
        product.setProductId("42198464-fd28-4cbd-a9bd-814e0f859f55");
    }

    @Test
    void testCreateAndFind() {
        when(productRepository.create(product)).thenReturn(product);
        List<Product> prodList = new ArrayList<>();
        prodList.add(product);
        when(productRepository.findAll()).thenReturn(prodList.iterator());

        service.create(product);

        List<Product> productList = service.findAll();
        Iterator<Product> productIterator = productList.iterator();

        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateNegative() {
        product.setProductQuantity(0);

        List<Product> prodList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(prodList.iterator());

        service.create(product);

        List<Product> productList = service.findAll();
        Iterator<Product> productIterator = productList.iterator();

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDelete() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        when(productRepository.delete(product)).thenAnswer(invocation -> productList.remove(product));

        service.delete(product);

        List<Product> products = service.findAll();
        assertFalse(products.iterator().hasNext());
    }

    @Test
    void testDeleteNegative() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        service.delete(null);

        List<Product> products = service.findAll();
        assertTrue(products.iterator().hasNext());
        assertEquals(products.getFirst().getProductId(), product.getProductId());
    }

    @Test
    void testGet() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        when(productRepository.get(product.getProductId())).thenReturn(product);

        Product retrievedProduct = service.get(product.getProductId());

        Iterator<Product> productIterator = service.findAll().iterator();
        Product savedProduct = productIterator.next();
        assertEquals(savedProduct.getProductId(), retrievedProduct.getProductId());
        assertEquals(savedProduct.getProductName(), retrievedProduct.getProductName());
        assertEquals(savedProduct.getProductQuantity(), retrievedProduct.getProductQuantity());
    }

    @Test
    void testEdit() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Product productEdit = new Product();
        productEdit.setProductName("Wuling SPW");
        productEdit.setProductQuantity(1863);
        productEdit.setProductId(product.getProductId());

        when(productRepository.findAll()).thenReturn(productList.iterator());
        when(productRepository.edit(productEdit, product.getProductId()))
                .thenAnswer(invocation -> productList.set(0, productEdit));

        service.edit(productEdit, product.getProductId());

        Iterator<Product> productIterator = service.findAll().iterator();
        Product savedProduct = productIterator.next();
        assertEquals(savedProduct.getProductId(), productEdit.getProductId());
        assertEquals(savedProduct.getProductName(), productEdit.getProductName());
        assertEquals(savedProduct.getProductQuantity(), productEdit.getProductQuantity());
    }

    @Test
    void testEditNegative() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Product productEdit = new Product();
        productEdit.setProductName("Wuling SPW");
        productEdit.setProductQuantity(0);
        productEdit.setProductId(product.getProductId());


        when(productRepository.findAll()).thenReturn(productList.iterator());
        when(productRepository.edit(productEdit, product.getProductId()))
                .thenAnswer(invocation -> productList.set(0, productEdit));
        when(productRepository.delete(productEdit)).thenAnswer(invocation -> productList.remove(productEdit));

        service.edit(productEdit, productEdit.getProductId());

        List<Product> products = service.findAll();
        assertFalse(products.iterator().hasNext());
    }

    @Test
    void testIncrementDecrement() {
        product.setProductQuantity(1);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.increment(product)).thenAnswer(invocation -> {
            product.setProductQuantity(product.getProductQuantity() + 1); // Incrementing product quantity
            return true; // Return true indicating successful increment
        });

        when(productRepository.decrement(product)).thenAnswer(invocation -> {
            product.setProductQuantity(product.getProductQuantity() - 1); // Incrementing product quantity
            return true; // Return true indicating successful increment
        });

        when(productRepository.delete(product))
                .thenAnswer(invocation -> productList.remove(product));

        when(productRepository.findAll()).thenReturn(productList.iterator());

        service.increment(product);
        assertEquals(2, productList.getFirst().getProductQuantity());
        service.decrement(product);
        assertEquals(1, productList.getFirst().getProductQuantity());
        service.decrement(product);

        List<Product> products = service.findAll();
        assertFalse(products.iterator().hasNext());
    }
}