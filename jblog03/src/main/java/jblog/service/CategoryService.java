package jblog.service;

import jblog.dto.CategoryAndPostCount;
import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.CategoryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    public CategoryService(CategoryRepository categoryRepository, PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
    }

    public List<CategoryVo> findAllByBlogId(String userId) {
        return categoryRepository.findAllCategoryByUserId(userId);
    }

    public List<CategoryAndPostCount> findAllCategoryByBlogId(String userId) {
        return categoryRepository.countCategoryByUserId(userId);
    }

    @Transactional
    public boolean deleteCategory(int categoryId) {
        if (categoryRepository.existCategoryById(categoryId)) {
            return false;
        }
        postRepository.deletePostById(categoryId);
        categoryRepository.deleteCategoryById(categoryId);
        return true;
    }

    public void insertCategory(String id, String categoryName, String description) {
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setBlogId(id);
        categoryVo.setDescription(description);
        categoryVo.setName(categoryName);
        categoryRepository.insertCategory(categoryVo);
    }
}
