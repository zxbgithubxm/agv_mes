package main.java.cn.ac.casqiem.agv.orderchain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    private Boolean cycle;
    private Integer priority;
    private Integer domainId;
    private Integer groupId;
    private String name;

}
