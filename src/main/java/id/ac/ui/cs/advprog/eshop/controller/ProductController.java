package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String home() {
        return "Home";
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
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable String id) {
        Product product = service.get(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product) {
        service.edit(product);
        return "redirect:list";
    }

    @GetMapping("/increment/{id}")
    public String incrementProductPost(@PathVariable String id) {
        Product product = service.get(id);
        service.increment(product);
        return "redirect:/product/list";
    }

    @GetMapping("/decrement/{id}")
    public String decrementProductPost(@PathVariable String id) {
        Product product = service.get(id);
        service.decrement(product);
        return "redirect:/product/list";
    }

 }
