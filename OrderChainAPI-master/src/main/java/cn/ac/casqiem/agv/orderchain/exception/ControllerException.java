package main.java.cn.ac.casqiem.agv.orderchain.exception;

import lombok.Data;

import java.io.IOException;

@Data
public class ControllerException extends IOException {
    private String code;

    public ControllerException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

}
