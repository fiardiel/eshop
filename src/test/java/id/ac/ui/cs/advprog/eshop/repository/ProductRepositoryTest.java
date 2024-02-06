package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.bouncycastle.util.Arrays;
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
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de47-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Bambang");
        product2.setProductQuantity(50);
        productRepository.create(product2);

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
        Product product1 = new Product();
        product1.setProductId("3b0a3b55-fb18-4e2b-b4b3-f789c3f52d22");
        product1.setProductName("Sabun Cuci Joni");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        productRepository.delete(product1);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        Product product1 = new Product(), product2 = new Product();
        product1.setProductId("0e4000be-56dc-4f75-854e-5063273ab2dc");
        product1.setProductName("Teh Wuling Pejuang");
        product1.setProductQuantity(5);
        productRepository.create(product1);

        product2.setProductId("d009dbb5-2e14-4a0c-94d6-eb4a9eebb0f7");
        product2.setProductName("CRV Depok ZJC");
        product2.setProductQuantity(2);
        productRepository.create(product2);

        productRepository.delete(product1);
        Iterator<Product> productIterator = productRepository.findAll();
        Product curProduct = productIterator.next();
        assertNotEquals(curProduct, product1);
        assertEquals(curProduct, product2);
    }

    @Test
    void testDeleteNonExistingProduct() {
        Product product = new Product();
        product.setProductId("3b0a3b55-fb18-4e2b-b4b3-f789c3f52d22");
        product.setProductName("Sabun Cuci Joni");
        product.setProductQuantity(1);
        productRepository.create(product);

        Product dummyProduct = new Product();
        dummyProduct.setProductId("dummy");
        productRepository.delete(dummyProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }


    @Test
    void testEdit() {
        String id = "0e4000be-56dc-4f75-854e-5063273ab2dc"; String name = "Rakha Strong Man Sabab"; int qty = 5;
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(qty);
        productRepository.create(product);
        
        String editedName = "Alice Baswedan";
        int editedQuantity = 1;
        product.setProductName(editedName); product.setProductQuantity(editedQuantity);
        productRepository.edit(product, id);

        Iterator<Product> productIterator = productRepository.findAll();
        Product editedProduct = null;
        while (productIterator.hasNext()) {
            Product curProduct = productIterator.next();
            if (curProduct.getProductId().equals(product.getProductId())) {
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
        Product product = new Product();
        product.setProductId("0e4000be-56dc-4f75-854e-5063273ab2dc");
        product.setProductName("Thamudz MD");
        product.setProductQuantity(3);
        productRepository.create(product);

        String dummyId = "dummyId";
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
}
