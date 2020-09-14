package main.java.cn.ac.casqiem.agv.orderchain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderChainRequest implements Serializable {
    private Task task;
    private List<Integer> orderChainIDList;
    private List<String> orderChainNameList;
}
