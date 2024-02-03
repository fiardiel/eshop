package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public boolean delete(Product product);
    public Product get(String id);
    public Product edit(Product product);
    public Product increment(Product product);
    public Product decrement(Product product);
    public List<Product> findAll();
}
