package jblog.repository;

import jblog.vo.BlogVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepository {
    private final SqlSession sqlSession;

    public BlogRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public boolean makeInitBlog(String userId) {
        return sqlSession.insert("blog.makeInitBlog", new BlogVo(userId,userId+"'s 블로그")) >= 1;
    }

    public List<BlogVo> findAll() {
        return sqlSession.selectList("blog.findAll");
    }

    public void updateBlog(BlogVo blogVo) {
        sqlSession.update("blog.updateBlog", blogVo);
    }
}
