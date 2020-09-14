package main.java.cn.ac.casqiem.agv.orderchain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeOrderResponse {
    private String message;
    private Boolean status;
}
