package app.blogging.blogging_application.services;

import java.util.List;

import app.blogging.blogging_application.dto.CategoryDto;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    void deleteCategory(Integer categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Integer categoryId);

}
