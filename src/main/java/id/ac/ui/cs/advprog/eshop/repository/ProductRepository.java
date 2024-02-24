package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepository {
    public Product create(Product product);
    public boolean delete(Product product);
    public Product edit(Product product, String id);
    public boolean increment(Product product);
    public boolean decrement(Product product);
    public Product get(String id);
    public Iterator<Product> findAll();
}
