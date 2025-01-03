package app.blogging.blogging_application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.blogging.blogging_application.dto.CategoryDto;
import app.blogging.blogging_application.services.CategoryService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> postCategory(@Valid @RequestBody CategoryDto categoryDto) {
        //TODO: process POST request
        CategoryDto categoryDtoReturn = this.categoryService.addCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(categoryDtoReturn, HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> putCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        //TODO: process PUT request
        CategoryDto categoryDtoReturn = this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<CategoryDto>(categoryDtoReturn, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categoryDtos = this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getMethodName(@PathVariable Integer id) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMethodName(@PathVariable Integer id) {
        this.categoryService.deleteCategory(id);
    }
    
    
}
