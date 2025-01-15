package jblog.event;

import jblog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BeanRefreshEventListener {
    @Autowired
    private ConfigurableApplicationContext context;

    @EventListener
    public void handleBeanRefreshEvent(BeanRefreshEvent event) {
        if ("blogTitle".equals(event.getBeanName())) {
            Map<String, BlogVo> blogTitleMap = (Map<String, BlogVo>) context.getBean("blogTitle");
            BlogVo updatedBlog = (BlogVo) event.getBean();
            blogTitleMap.put(updatedBlog.getUserId(), updatedBlog);
        }
    }
}

