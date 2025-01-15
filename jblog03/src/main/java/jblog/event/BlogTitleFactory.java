package jblog.event;

import jblog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BlogTitleFactory {
    @Autowired
    private ApplicationContext context;

    public Map<String, BlogVo> getBlogTitle() {
        return context.getBean("blogTitle", Map.class);
    }
}

