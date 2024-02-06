package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public void create(Product product) {
        productData.add(product);
    }

    public void delete(Product product) {
        productData.remove(product);
    }

    public void edit(Product product, String id) {
        for (int i=0; i < productData.size(); i++) {
            Product curProduct = productData.get(i);
            if (curProduct.getProductId().equals(id)) {
                product.setProductId(id);
                productData.set(i, product);
                return;
            }
        }
        // Product not found
    }

    public void increment(Product product) {
        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(product.getProductId())) {
                curProduct.setProductQuantity(curProduct.getProductQuantity() + 1);
                return;
            }
        }
    }

    public void decrement(Product product) {
        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(product.getProductId())) {
                curProduct.setProductQuantity(curProduct.getProductQuantity()-1);
                return;
            }
        }
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
