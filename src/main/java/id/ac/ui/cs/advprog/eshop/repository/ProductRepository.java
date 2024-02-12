package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product edit(Product product, String id) {
        for (int i=0; i < productData.size(); i++) {
            Product curProduct = productData.get(i);
            if (curProduct.getProductId().equals(id)) {
                product.setProductId(id);
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }

    public boolean increment(Product product) {
        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(product.getProductId())) {
                curProduct.setProductQuantity(curProduct.getProductQuantity() + 1);
                return true; // Increment successful
            }
        }
        return false; // Product not found
    }

    public boolean decrement(Product product) {
        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(product.getProductId())) {
                curProduct.setProductQuantity(curProduct.getProductQuantity()-1);
                return true;
            }
        }
        return false;
    }

    public Product get(String id) {
        Iterator<Product> productIterator = productData.iterator();
        while (productIterator.hasNext()) {
            Product curProduct = productIterator.next();
            if (curProduct.getProductId().equals(id)) {
                return curProduct;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
