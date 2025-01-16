package jblog.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.dto.JsonResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogNotFoundException.class)
    public String handleBlogNotFoundException(BlogNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/BlogNotFound";
    }

    @ExceptionHandler(Exception.class)
    public void handler(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        String accept = request.getHeader("accept");

        if (accept.matches(".*application/json.*")) {
            // 3. JSON 응답
            JsonResult jsonResult = JsonResult.fail(sw.toString());
            String jsonString = new ObjectMapper().writeValueAsString(jsonResult);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
        } else {
            if (e instanceof NoHandlerFoundException || e instanceof NoResourceFoundException) {
                request.getRequestDispatcher("/WEB-INF/views/errors/404.jsp").forward(request, response);
            }
//            else {
//                request.setAttribute("errors", sw.toString());
//                request.getRequestDispatcher("/WEB-INF/views/errors/500.jsp").forward(request, response);
//            }
        }
    }
}
