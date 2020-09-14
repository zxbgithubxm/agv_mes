package test.java.cn.ac.casqiem.agv.orderchain;

import cn.ac.casqiem.agv.orderchain.entity.OrderChainTemplate;
import cn.ac.casqiem.agv.orderchain.service.OrderChainTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderChainTemplateServiceTest {

    @Autowired
    private OrderChainTemplateService orderChainTemplateService;

    @Test
    public void getOneByName() {
        OrderChainTemplate orderChainTemplate = orderChainTemplateService.getOneByName("5G测试");
        System.out.println(orderChainTemplate);
    }
}
