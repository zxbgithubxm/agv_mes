package main.java.cn.ac.casqiem.agv.orderchain.exception;

import lombok.Data;

@Data
public class ControllerExceptionResponse {
    private String message;
    private String code;

    public ControllerExceptionResponse(ControllerException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public ControllerExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
