package com.spring.ecommerce.service;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.CategoryRepository;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategory(int id) {
        return categoryRepository.findById(id).get();
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}
