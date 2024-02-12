package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Set up the mock behavior
        when(service.create(any(Product.class))).thenReturn(product); // Use any() matcher

        // Perform the POST request
        mockMvc.perform(post("/product/create")
                        .param("productName", "Test Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        // Verify that the create method of the service is called with the expected product instance
        verify(service, times(1)).create(any(Product.class)); // Use any() matcher
    }


    @Test
    void testProductListPage() throws Exception {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        Product product2 = new Product();
        product2.setProductName("Product 2");

        List<Product> productList = Arrays.asList(product1, product2);

        when(service.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ListProduct"))
                .andExpect(model().attribute("products", productList));
    }

    @Test
    void testDeletePost() throws Exception {
        Product product = new Product();
        product.setProductId("1");

        when(service.get("1")).thenReturn(product);

        mockMvc.perform(get("/product/delete/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).delete(product);
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");

        when(service.get("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPost() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Edited Product");
        product.setProductQuantity(20);

        mockMvc.perform(post("/product/edit/{id}", "1")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).edit(product, "1");
    }

    @Test
    void testIncrementProductPost() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductQuantity(10);

        when(service.get("1")).thenReturn(product);

        mockMvc.perform(get("/product/increment/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).increment(product);
    }

    @Test
    void testDecrementProductPost() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductQuantity(10);

        when(service.get("1")).thenReturn(product);

        mockMvc.perform(get("/product/decrement/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).decrement(product);
    }

}
