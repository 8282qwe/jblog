package jblog.repository;

import jblog.dto.PostDtoForSelect;
import jblog.vo.PostVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final SqlSession sqlSession;

    public PostRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<PostVo> findAllBySpec(PostDtoForSelect postDtoForSelect) {
        return sqlSession.selectList("post.findAllBySpec", postDtoForSelect);
    }

    public void deletePostById(int categoryId) {
        sqlSession.delete("post.deletePostById", categoryId);
    }

    public boolean insertPost(PostVo postVo) {
        return sqlSession.insert("post.insertPost", postVo) >= 1;
    }
}
