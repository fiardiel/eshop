package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    private int id = 0;

    @Override
    public Product create(Product product) {
        if (product.getProductQuantity() > 0) {
            product.setProductId(Integer.toString(++id));
            productRepository.create(product);
            return product;
        }
        return null;
    }

    @Override
    public boolean delete(int id){
        Product product = get(id);
        return product != null && productRepository.delete(product);
    }

    @Override
    public Product edit(Product product) {
        if (product.getProductQuantity() > 0) {
            productRepository.edit(product);
            return product;
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    public Product get(int id) {
        Product product = null;
        Iterator<Product> products = productRepository.findAll();
        while (products.hasNext()) {
            Product cur = products.next();
            if (cur.getProductId().equals(Integer.toString(id))) {
                product = cur;
                break;
            }
        }
        return product;
    }

    public Product increment(Product product) {
        return productRepository.increment(product);
    }

    public Product decrement(Product product) {
        if (product.getProductQuantity() > 1) {
            return productRepository.decrement(product);
        }
        productRepository.delete(product);
        return product;
    }
}
