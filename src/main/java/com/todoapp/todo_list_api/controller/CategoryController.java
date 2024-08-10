package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.service.ICategoryService;
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
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // Read all categories.
    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categoryList = categoryService.getCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    // Read one category.
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Category category = optionalCategory.get();

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Update a whole category.
    @PutMapping("/edit")
    public ResponseEntity<Category> editCategory(@RequestBody Category category) {
        Category c = categoryService.saveCategory(category);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // Update category by its ID.
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(
            @PathVariable Long id,
            @RequestBody Category category) {

        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        categoryService.editCategory(id, category);
        Category c = optionalCategory.get();
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


    // Delete a category by its ID.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("The client was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The was an error", HttpStatus.BAD_REQUEST);
        }
    }
}
