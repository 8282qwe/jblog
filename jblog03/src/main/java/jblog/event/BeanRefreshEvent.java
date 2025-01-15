package jblog.event;

public class BeanRefreshEvent {
    private final String beanName;
    private final Object bean;

    public BeanRefreshEvent(String beanName, Object bean) {
        this.beanName = beanName;
        this.bean = bean;
    }

    // Getters

    public String getBeanName() {
        return beanName;
    }

    public Object getBean() {
        return bean;
    }
}

