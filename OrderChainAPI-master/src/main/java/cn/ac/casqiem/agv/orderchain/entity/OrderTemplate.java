package main.java.cn.ac.casqiem.agv.orderchain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderTemplate implements Serializable {
    private Integer id;
    private Integer orderChainTemplateId;
    private Integer mapId;
    private Integer action;
    private Integer endPointId;
    private Integer orient;
    private Double offset;
    private Integer layingoff;
    private String info;
    private Integer orderIndex;
}
