package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.ProductDTO;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.CategoryService;
import com.spring.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@Controller
public class AdminController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories" , categories);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategories(Model model) {
        model.addAttribute("category" , new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update")
    public String getCategory(@RequestParam("category_id") int id  , Model model) {
        Category category = categoryService.getCategory(id);
        model.addAttribute("category" , category);
        return "categoriesAdd";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String removeCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

//    *************** Product Section *************

    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products" , products);
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productDTO" , new ProductDTO());
        model.addAttribute("categories" , categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String postProduct(@ModelAttribute ProductDTO productDTO) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setCategory(categoryService.getCategory(productDTO.getCategoryID()));

        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String removeProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{id}")
    public String getProduct(@PathVariable Long id , Model model) {
        Product product = productService.getProduct(id);

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setWeight(product.getWeight());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryID(product.getCategory().getId());

        model.addAttribute("categories" , categoryService.getAllCategory());
        model.addAttribute("productDTO" , productDTO);

        return "productsAdd";
    }

}
