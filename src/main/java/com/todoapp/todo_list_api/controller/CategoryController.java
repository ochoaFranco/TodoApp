package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.CategoryRequestDTO;
import com.todoapp.todo_list_api.dto.CategoryResponseDTO;
import com.todoapp.todo_list_api.exception.DuplicateCategoryNameException;
import com.todoapp.todo_list_api.exception.ErrorResponse;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.service.ICategoryService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.hibernate.exception.ConstraintViolationException;
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
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryRequestDTO categoryDTO) {
        try {
            CategoryResponseDTO categoryResponseDTO = categoryService.saveCategory(categoryDTO);
            return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);

            // Handle duplicated name exception
        } catch (DuplicateCategoryNameException exception) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Category name already exists",
                    "A category with this name is already registered. Please use a different name.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // testing  purposes.
        }
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
        } catch (ConstraintViolationException ex) {
            return new ResponseEntity<>("No se puede eliminar la categoria, tiene una tarea asociada", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There was an error", HttpStatus.BAD_REQUEST);
        }
    }
}
