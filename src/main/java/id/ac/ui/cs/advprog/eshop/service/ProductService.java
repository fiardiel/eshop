package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public boolean delete(int id);
    public Product get(int id);
    public Product edit(Product product);
    public List<Product> findAll();
}
