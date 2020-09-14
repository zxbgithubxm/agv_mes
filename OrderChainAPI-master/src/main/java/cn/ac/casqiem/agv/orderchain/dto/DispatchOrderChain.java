package main.java.cn.ac.casqiem.agv.orderchain.dto;

import cn.ac.casqiem.agv.orderchain.entity.OrderTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatchOrderChain implements Serializable {

    private Task po;
    private List<OrderTemplate> orders;
}
