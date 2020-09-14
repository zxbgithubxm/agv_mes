package main.java.cn.ac.casqiem.agv.orderchain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderChainTemplate implements Serializable {
    private Integer id;
    private String name;
    private Integer groupId;
    private Integer priority;
    private Integer domainId;
    private Integer workerId;
    private Integer enable;
    private Integer deleteFlag;
    private LocalDateTime insertTime;
    private Integer cycle;
}
