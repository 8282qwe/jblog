package jblog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.annotation.Authorization;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

public class AuthorizationInterceptor implements HandlerInterceptor {
    private final ApplicationContext applicationContext;

    public AuthorizationInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    //로그인이 필수 이고, 그 중 role에 따라 분기하는 방법.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) return true;

        Authorization annotation = handlerMethod.getMethodAnnotation(Authorization.class);

        if (annotation == null) annotation = handlerMethod.getBeanType().getAnnotation(Authorization.class);

        if (annotation == null) return true;
        Map<String, BlogVo> blogVoMap = (Map<String, BlogVo>) applicationContext.getBean("blogTitle");


        String role = annotation.role();

        String urlPath = request.getServletPath().split("/")[1];
        request.setAttribute("path", urlPath);
//        request.setAttribute("title", blogVoMap.get(urlPath).getTitle());
//        request.setAttribute("profile", blogVoMap.get(urlPath).getProfile());

        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("user");

        if (userVo == null) {
            if ("ANY".equals(role)) {
                return true;
            }
            else {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        request.setAttribute("admin", userVo.getId().equals(urlPath));

        if ("ADMIN".equals(role)) {
            if (userVo.getId().equals(urlPath)) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath()+"/"+urlPath);
                return false;
            }
        }

        return true;
    }
}
