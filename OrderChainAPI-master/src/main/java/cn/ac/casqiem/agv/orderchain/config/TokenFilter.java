package main.java.cn.ac.casqiem.agv.orderchain.config;

import cn.ac.casqiem.agv.orderchain.exception.ControllerException;
import cn.ac.casqiem.agv.orderchain.exception.ControllerExceptionResponse;
import cn.ac.casqiem.agv.orderchain.exception.ResponseCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class TokenFilter extends OncePerRequestFilter {

    /**
     * 存放Token的Header Key
     */

    private static final String HEADER_STRING = "Token";

    @Autowired
    private RCSProperties rcsProperties;
    private static final String pattern = "/dispatch/.*";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String token = request.getHeader(HEADER_STRING);
            String path = request.getServletPath();
            Boolean isMatch = Pattern.matches(pattern, path);
            if (!rcsProperties.getToken().equals(token) && isMatch) {
                ControllerExceptionResponse controllerExceptionResponse = new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.ERROR_TOKEN));
                response.setContentType("application/json;charset=utf-8");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(controllerExceptionResponse));
                return;
            }
        } catch (Exception e) {
            ControllerExceptionResponse controllerExceptionResponse = new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.ERROR_SYSTEM));
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().print(mapper.writeValueAsString(controllerExceptionResponse));
            return;
        }
        chain.doFilter(request, response);
    }

}
