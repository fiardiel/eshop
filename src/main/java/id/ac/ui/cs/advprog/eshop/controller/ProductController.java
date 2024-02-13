package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ListProduct";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable String id) {
        Product product = service.get(id);
        service.delete(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable String id) {
        Product product = service.get(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@ModelAttribute Product product, @PathVariable String id) {
        service.edit(product, id);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/increment/{id}")
    public String incrementProductPost(@PathVariable String id) {
        Product product = service.get(id);
        service.increment(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/decrement/{id}")
    public String decrementProductPost(@PathVariable String id) {
        Product product = service.get(id);
        service.decrement(product);
        return REDIRECT_PRODUCT_LIST;
    }

 }
