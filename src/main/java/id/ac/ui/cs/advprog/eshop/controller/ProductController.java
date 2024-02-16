package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
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

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

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

@Controller
@RequestMapping("/car")
class CarController extends ProductController {

    @Autowired
    private CarService carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car) {
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar/{carId}")
    public String editCarPost(@ModelAttribute Car car) {
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);

        return "redirect:/car/listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}

