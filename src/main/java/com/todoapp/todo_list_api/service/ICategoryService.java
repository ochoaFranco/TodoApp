package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.CategoryRequestDTO;
import com.todoapp.todo_list_api.dto.CategoryResponseDTO;
import com.todoapp.todo_list_api.model.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    // creates a category.
    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryDTO);
    // Get all categories.
    List<CategoryResponseDTO> getCategories();
    // get one category.
    CategoryResponseDTO getCategoryById(Long id);
    // update a category.
    CategoryResponseDTO editCategory(Long id, CategoryRequestDTO categoryRequestDTO);
    // delete a category.
    void deleteCategory(Long id);
}
