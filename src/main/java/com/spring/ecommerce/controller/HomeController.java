package com.spring.ecommerce.controller;


import com.spring.ecommerce.service.CategoryService;
import com.spring.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @GetMapping({"/" , "/home"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories" , categoryService.getAllCategory());
        model.addAttribute("products" , productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String getCategories(@PathVariable int id , Model model) {
        model.addAttribute("categories" , categoryService.getAllCategory());
        model.addAttribute("products" , productService.getAllProductsByCategoryId(id));
        return "shop";
    }


    @GetMapping("/shop/viewProduct")
    public String viewProduct(@RequestParam("product_id") Long id , Model model) {
        model.addAttribute("product" , productService.getProduct(id));
        return "viewProduct";
    }

}
