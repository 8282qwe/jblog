package jblog.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.dto.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class GlobalExceptionHandler {

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
            // 4. 사과 페이지 (종료)
            response.setContentType("text/html;charset=utf-8");
            request.setAttribute("errors", sw.toString());
            request.getRequestDispatcher("/WEB-INF/views/errors/exception.jsp").forward(request, response);
        }
    }
}
