package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.CategoryRequestDTO;
import com.todoapp.todo_list_api.dto.CategoryResponseDTO;
import com.todoapp.todo_list_api.exception.DuplicateCategoryNameException;
import com.todoapp.todo_list_api.exception.ErrorResponse;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a new category", description = "Create a new category in the system")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
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

    @Operation(summary = "Get all categories", description = "Retrieve all categories from the system")
    @ApiResponse(responseCode = "200", description = "Categories retrieved successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
    // Read all categories.
    @GetMapping()
    public ResponseEntity<?> getCategories() {
        try {
            List<CategoryResponseDTO> categoryList = categoryService.getCategories();
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "An error has occurred", "Could not retrieve categories."
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get one category", description = "Retrieve a category from the system")
    @ApiResponse(responseCode = "200", description = "Category retrieved successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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
    @Operation(summary = "Edit a category", description = "Takes a category as parameter to be edited")
    @ApiResponse(responseCode = "200", description = "Category has been edited successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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
    @Operation(summary = "Delete a category", description = "Takes a category as parameter to be deleted")
    @ApiResponse(responseCode = "200", description = "Category has been deleted successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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
