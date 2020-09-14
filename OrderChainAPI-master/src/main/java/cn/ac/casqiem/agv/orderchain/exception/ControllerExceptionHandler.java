package main.java.cn.ac.casqiem.agv.orderchain.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * 参考：SpringBoot 使用异常自定义错误码</https://blog.csdn.net/shida_csdn/article/details/81002198>
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ControllerExceptionResponse handleException(HttpServletRequest request, Exception ex) {
        if (ex instanceof ControllerException) {
            log.warn(ex.getMessage(), ex);
            return new ControllerExceptionResponse((ControllerException) ex);
        } else {
            log.error(ex.getMessage(), ex);
            return new ControllerExceptionResponse("B0001", ex.getMessage());
        }
    }

}
