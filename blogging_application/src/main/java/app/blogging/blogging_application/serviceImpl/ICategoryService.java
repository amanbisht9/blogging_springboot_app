package app.blogging.blogging_application.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.blogging.blogging_application.config.ModelMapperConfig;
import app.blogging.blogging_application.dto.CategoryDto;
import app.blogging.blogging_application.entity.Category;
import app.blogging.blogging_application.exceptions.CreationException;
import app.blogging.blogging_application.exceptions.FetchException;
import app.blogging.blogging_application.repositories.CategoryRepository;
import app.blogging.blogging_application.services.CategoryService;


@Service
public class ICategoryService implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        //Add category to database
        try {
            Category category = modelMapperConfig.modelMapper().map(categoryDto, Category.class);
            Category savedCategory = this.categoryRepository.save(category);
            return this.modelMapperConfig.modelMapper().map(savedCategory, CategoryDto.class);

        } catch (Exception e) {
            throw new CreationException("Cannot create the category", e);
        }
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        // update the category
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new FetchException("Category id: "+String.valueOf(categoryId)+" not found", new Throwable().getCause()));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedUser = this.categoryRepository.save(category);

        return this.modelMapperConfig.modelMapper().map(updatedUser, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        //delete the category by id
        this.categoryRepository.findById(categoryId).orElseThrow(() -> new FetchException("Category id: "+String.valueOf(categoryId)+" not found",  new Throwable().getCause()));
        this.categoryRepository.deleteById(categoryId);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        // get all category 
        try {
            List<Category> categories = this.categoryRepository.findAll();
            return categories.stream()
                       .map(c -> this.modelMapperConfig.modelMapper().map(c, CategoryDto.class))
                       .collect(Collectors.toList());

        } catch (Exception e) {
            // Throw exception
            throw new FetchException("Cannot get all categories.", e);
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        // Get category by id
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new FetchException("Category id: "+String.valueOf(categoryId)+" not found",  new Throwable().getCause()));
        return this.modelMapperConfig.modelMapper().map(category, CategoryDto.class);
    }

}
