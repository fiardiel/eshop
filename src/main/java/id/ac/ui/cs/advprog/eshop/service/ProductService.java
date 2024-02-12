package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public void delete(Product product);
    public Product get(String id);
    public void edit(Product product, String id);
    public void increment(Product product);
    public void decrement(Product product);
    public List<Product> findAll();
}
