package com.todoapp.todo_list_api.mapper;

import com.todoapp.todo_list_api.dto.CategoryRequestDTO;
import com.todoapp.todo_list_api.dto.CategoryResponseDTO;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    // Take a request DTO and returns an entity with its values set.
    public Category toCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());

        return category;
    }

    // takes an entity and returns a dto.
    public CategoryResponseDTO categoryToResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getName());
    }

    public Category setAttributes(Category category, CategoryRequestDTO categoryRequestDTO) {
        category.setName(categoryRequestDTO.getName());
        return category;
    }
}
