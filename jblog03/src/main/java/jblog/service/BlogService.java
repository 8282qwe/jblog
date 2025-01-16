package jblog.service;

import jblog.event.BeanRefreshEvent;
import jblog.event.BlogTitleFactory;
import jblog.exception.BlogNotFoundException;
import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogTitleFactory blogTitleFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public BlogService(BlogRepository blogRepository, BlogTitleFactory blogTitleFactory) {
        this.blogRepository = blogRepository;
        this.blogTitleFactory = blogTitleFactory;
    }

    public List<BlogVo> findAll() {
        return blogRepository.findAll();
    }

    public void editBlog(String id, String title, String filename) {
        Map<String, BlogVo> blogVoMap = (Map<String, BlogVo>) applicationContext.getBean("blogTitle");
        BlogVo oldBlogVo = blogVoMap.get(id);

        BlogVo newBlogVo = new BlogVo();
        newBlogVo.setUserId(oldBlogVo.getUserId());
        newBlogVo.setTitle(title);
        if (!filename.isBlank()){
            newBlogVo.setProfile(filename);
        }
        else {
            newBlogVo.setProfile(oldBlogVo.getProfile());
        }

        eventPublisher.publishEvent(new BeanRefreshEvent("blogTitle", newBlogVo));

        blogRepository.updateBlog(newBlogVo);
    }

    public void checkBlog(String id) {
        if (!blogTitleFactory.getBlogTitle().containsKey(id)) {
            throw new BlogNotFoundException("blog does not exist");
        }
    }
}
