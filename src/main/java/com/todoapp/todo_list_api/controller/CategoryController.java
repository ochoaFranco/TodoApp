package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.CategoryRequestDTO;
import com.todoapp.todo_list_api.dto.CategoryResponseDTO;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.service.ICategoryService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    // Create a category.
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @Valid @RequestBody CategoryRequestDTO categoryDTO) {
        CategoryResponseDTO categoryResponseDTO = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    // Read all categories.
    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        List<CategoryResponseDTO> categoryList = categoryService.getCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    // Read one category.
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        try {
            CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(id);
            return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update category by its ID.
    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDTO> editCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {

        try {
            CategoryResponseDTO categoryResponseDTO = categoryService.editCategory(id, categoryRequestDTO);
            return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a category by its ID.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("The category was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The was an error", HttpStatus.BAD_REQUEST);
        }
    }
}
