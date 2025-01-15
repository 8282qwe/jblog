package jblog.repository;

import jblog.dto.CategoryAndPostCount;
import jblog.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    private final SqlSession sqlSession;

    public CategoryRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public boolean makeInitCategory(String userId) {
        return sqlSession.insert("category.makeInitCategory",userId) >= 1;
    }

    public List<CategoryVo> findAllCategoryByUserId(String userId) {
        return sqlSession.selectList("category.findAllCategoryByUserId", userId);
    }

    public List<CategoryAndPostCount> countCategoryByUserId(String userId) {
        return sqlSession.selectList("category.countCategoryByUserId", userId);
    }

    public void deleteCategoryById(int categoryId) {
        sqlSession.delete("category.deleteCategoryById", categoryId);
    }

    public void insertCategory(CategoryVo categoryVo) {
        sqlSession.insert("category.insertCategory", categoryVo);
    }

    public boolean existCategoryById(int categoryId) {
        return sqlSession.selectOne("category.existCategoryById", categoryId);
    }
}
