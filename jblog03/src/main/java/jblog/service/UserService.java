package jblog.service;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    public UserService(UserRepository userRepository, BlogRepository blogRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public boolean joinUser(UserVo userVo) {
        return userRepository.insertUser(userVo) && blogRepository.makeInitBlog(userVo.getId()) && categoryRepository.makeInitCategory(userVo.getId());
    }

    public boolean checkUserById(String userId) {
        return userRepository.findByUserId(userId) != null;
    }

    public UserVo getUserByIdAndPassword(UserVo userVo) {
        return userRepository.findByIdAndPassword(userVo);
    }
}
