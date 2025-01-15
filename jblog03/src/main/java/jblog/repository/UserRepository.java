package jblog.repository;

import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final SqlSession sqlSession;

    public UserRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public boolean insertUser(UserVo userVo) {
        return sqlSession.insert("user.insertUser", userVo) >= 1;
    }

    public UserVo findByUserId(String userId) {
        return sqlSession.selectOne("user.findByUserId", userId);
    }

    public UserVo findByIdAndPassword(UserVo userVo) {
        return sqlSession.selectOne("user.findByIdAndPassword", userVo);
    }
}
