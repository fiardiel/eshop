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
    public void edit(Product product) {
        if (product.getProductQuantity() > 0) {
            productRepository.edit(product);
        }
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    public Product get(String id) {
        Product product = null;
        Iterator<Product> products = productRepository.findAll();
        while (products.hasNext()) {
            Product cur = products.next();
            if (cur.getProductId().equals(id)){
                product = cur;
                break;
            }
        }
        return product;
    }

    public void increment(Product product) {
        productRepository.increment(product);
    }

    public void decrement(Product product) {
        if (product.getProductQuantity() > 1) {
            productRepository.decrement(product);
            return;
        }
        productRepository.delete(product);
    }
}
