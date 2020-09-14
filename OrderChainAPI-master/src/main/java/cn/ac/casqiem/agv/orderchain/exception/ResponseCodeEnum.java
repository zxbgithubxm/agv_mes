package main.java.cn.ac.casqiem.agv.orderchain.exception;

/**
 * 错误码列表
 *
 * 参考：Java开发手册（泰山版）
 *
 */
public enum  ResponseCodeEnum {
    OK("00000", "任务下发成功"),
    ERROR_CLIENT_RECORD_NOT_EXIST("A2001", "不存在该记录"),
    // 系统错误
    ERROR_SYSTEM("B0001", "系统执行出错"),
    //
    ERROR_TOKEN("E0004", "令牌错误"),
    ERROR_REQUEST_PARAMETER("E0005","任务链ID列表和名称列表参数至少有1个"),
    ERROR_REQUEST_PARAMETER_EMPTY("E0006","任务链ID列表和名称列表参数不能都为空");

    private String code;
    private String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
