package jblog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.UserService;
import jblog.vo.UserVo;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        UserVo userVo = userService.getUserByIdAndPassword(new UserVo(id, password));

        if (userVo != null) {
            request.getSession().setAttribute("user", userVo);

            response.sendRedirect(request.getContextPath());
            return false;
        }

        request.setAttribute("id", id);
        request.setAttribute("result", "fail");
        request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
        return false;
    }
}
