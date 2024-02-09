package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(Product product) {
        if (product.getProductQuantity() > 0) {
            product.setProductId(String.valueOf(UUID.randomUUID()));
            productRepository.create(product);
        }
    }

    @Override
    public void delete(Product product){
        if (product != null) {
            productRepository.delete(product);
        }
    }

    @Override
    public void edit(Product product, String id) {
        if (product.getProductQuantity() > 0) {
            productRepository.edit(product, id);
        }
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product get(String id) {
        return productRepository.get(id);
    }

    @Override
    public void increment(Product product) {
        productRepository.increment(product);
    }

    @Override
    public void decrement(Product product) {
        if (product.getProductQuantity() > 1) {
            productRepository.decrement(product);
            return;
        }
        productRepository.delete(product);
    }
}
