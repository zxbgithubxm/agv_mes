package main.java.cn.ac.casqiem.agv.orderchain.service;

import cn.ac.casqiem.agv.orderchain.entity.OrderChainTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderChainTemplateService extends IService<OrderChainTemplate> {

    OrderChainTemplate getOneByName(String name);
}
