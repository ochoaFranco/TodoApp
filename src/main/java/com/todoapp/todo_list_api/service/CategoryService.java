package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepo;

    // Create a category.
    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    // Get all categories.
    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    // Get one category given by its ID.
    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    // Update one category.
    @Override
    public Category updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory = this.getCategoryById(id);
        Category c = optionalCategory.get();
        // Setting attributes.
        if (category.getName() != null)
            c.setName(category.getName());

        return this.saveCategory(c);
    }

    // Delete one category by its ID.
    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
