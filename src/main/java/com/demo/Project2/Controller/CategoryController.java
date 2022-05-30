package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.Category;
import com.demo.Project2.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ArrayList<Category>> getCategory() {
        return ResponseEntity.status(200).body(categoryService.getCategory());
    }

    @PostMapping
    public ResponseEntity<Api> addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isCategoryAdded = categoryService.addCategory(category);
        if (!isCategoryAdded) {
            return ResponseEntity.status(500).body(new Api("Error Category is Invalid Added", 400));
        }
        return ResponseEntity.status(201).body(new Api("Category is Added", 201));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateCategory(@PathVariable Integer index, @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isCategoryEdit = categoryService.updateCategory(index, category);
        if (!isCategoryEdit) {
            return ResponseEntity.status(500).body(new Api("Error Category is Invalid Edited", 400));
        }
        return ResponseEntity.status(201).body(new Api("Category is Updated", 201));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeCategory(@RequestParam String id) {
        boolean isCategoryDeleted = categoryService.removeCategory(id);
        if (!isCategoryDeleted) {
            return ResponseEntity.status(500).body(new Api("Error Category is Invalid Deleted", 400));
        }
        return ResponseEntity.status(200).body(new Api("Deleted!", 200));
    }
}
