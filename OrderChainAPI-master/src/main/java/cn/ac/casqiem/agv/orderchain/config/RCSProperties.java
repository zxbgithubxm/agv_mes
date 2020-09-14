package main.java.cn.ac.casqiem.agv.orderchain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rcs")
@Data
public class RCSProperties {
    private String orderUrl;
    private String token;
    private String authUrl;
    private String apiFrom;
    private String apiToken;
}
