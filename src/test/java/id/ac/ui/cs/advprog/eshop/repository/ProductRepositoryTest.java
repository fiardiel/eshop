package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepositoryImpl productRepository;
    @InjectMocks
    Product product1;
    @InjectMocks
    Product product2;
    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Alice Bussedan");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        product2 = new Product();
        product2.setProductId("a0f9de47-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Prangko Subiangkot");
        product2.setProductQuantity(2);
        productRepository.create(product2);
    }
    @Test
    void testCreateAndFind() {
        productRepository.delete(product2);
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        productRepository.delete(product1);
        productRepository.delete(product2);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDelete() {
        productRepository.delete(product2);
        productRepository.delete(product1);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        productRepository.delete(product1);
        Iterator<Product> productIterator = productRepository.findAll();
        Product curProduct = productIterator.next();
        assertNotEquals(curProduct, product1);
        assertNull(productRepository.get(product1.getProductId()));
        assertEquals(product2.getProductId(), curProduct.getProductId());
    }

    @Test
    void testDeleteNonExistingProduct() {
        productRepository.delete(product2);

        Product dummyProduct = new Product();
        dummyProduct.setProductId("dummy");
        productRepository.delete(dummyProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }


    @Test
    void testEdit() {
        productRepository.delete(product2);

        String editedName = "Granger Prawono";
        int editedQuantity = 3;
        product1.setProductName(editedName);
        product1.setProductQuantity(editedQuantity);
        productRepository.edit(product1, product1.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        Product editedProduct = null;
        while (productIterator.hasNext()) {
            Product curProduct = productIterator.next();
            if (curProduct.getProductId().equals(product1.getProductId())) {
                editedProduct = curProduct;
                break;
            }
        }

        assertNotNull(editedProduct);
        assertEquals(editedProduct.getProductName(), editedName);
        assertEquals(editedProduct.getProductQuantity(), editedQuantity);
    }

    @Test
    void testEditNonExistingProduct() {
        productRepository.delete(product2);

        String dummyId = "42069";
        String dummyName = "dummyName";
        int dummyQuantity = 69;
        Product dummyProduct = new Product();
        dummyProduct.setProductId(dummyId);
        dummyProduct.setProductName(dummyName);
        dummyProduct.setProductQuantity(dummyQuantity);

        productRepository.edit(dummyProduct, dummyId);

        Iterator<Product> productIterator = productRepository.findAll();
        Product curProduct = productIterator.next();
        assertNotEquals(curProduct.getProductId(), dummyId);
        assertNotEquals(curProduct.getProductName(), dummyName);
        assertNotEquals(curProduct.getProductQuantity(), dummyQuantity);
    }

    @Test
    void testIncrement() {
        int qty = product1.getProductQuantity();
        productRepository.delete(product2);

        // Dummy data
        Product product3 = new Product();
        product3.setProductId("3b0a3b55-fb18-4e2b-b4b3-f789c3f52d22");
        product3.setProductName("Fried Chicken");
        product3.setProductQuantity(12);

        Iterator<Product> productIterator = productRepository.findAll();
        Product savedProduct = productIterator.next();
        productRepository.increment(savedProduct);
        productRepository.increment(product3);

        assertEquals(savedProduct.getProductQuantity(),qty + 1);
    }

    @Test
    void testDecrement() {
        int qty = product1.getProductQuantity();

        // Dummy data
        Product product3 = new Product();
        product3.setProductId("3b0a3b55-fb18-4e2b-b4b3-f789c3f52d22");
        product3.setProductName("Watermelon");
        product3.setProductQuantity(12);

        Iterator<Product> productIterator = productRepository.findAll();
        Product savedProduct = productIterator.next();
        productRepository.decrement(savedProduct);
        productRepository.decrement(product3);

        assertEquals(savedProduct.getProductQuantity(), qty - 1);
    }

    @Test
    void testGet() {
        Iterator<Product> productIterator = productRepository.findAll();
        Product curProduct = productIterator.next();
        assertEquals(productRepository.get(curProduct.getProductId()), product1);
    }

    @Test
    void testGetNegative() {
        productRepository.delete(product2);
        assertNull(productRepository.get(product2.getProductId()));
    }
}
