package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.model.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    // creates a category.
    Category saveCategory(Category category);
    // Get all categories.
    List<Category> getCategories();
    // get one category.
    Optional<Category> getCategoryById(Long id);
    // update a category.
    Category editCategory(Long id, Category category);
    // delete a category.
    void deleteCategory(Long id);
}
