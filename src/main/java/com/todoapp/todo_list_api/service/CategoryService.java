package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.CategoryRequestDTO;
import com.todoapp.todo_list_api.dto.CategoryResponseDTO;
import com.todoapp.todo_list_api.mapper.CategoryMapper;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepo;
    @Autowired
    private CategoryMapper mapper;

    // Create a category.
    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = mapper.toCategory(categoryRequestDTO);
        return mapper.categoryToResponseDTO(category);
    }

    // Get all categories.
    @Override
    public List<CategoryResponseDTO> getCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(category -> mapper.categoryToResponseDTO(category))
                .collect(Collectors.toList());
    }

    // Get one category given by its ID.
    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty())
            throw new RuntimeException();
        Category category = optionalCategory.get();
        return mapper.categoryToResponseDTO(category);
    }

    // Update one category.
    @Override
    public CategoryResponseDTO editCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty())
            throw new RuntimeException();
        Category category = optionalCategory.get();
        return mapper.categoryToResponseDTO(category);
    }

    // Delete one category by its ID.
    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
