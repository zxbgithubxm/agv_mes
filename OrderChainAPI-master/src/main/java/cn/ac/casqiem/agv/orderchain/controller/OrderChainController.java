package main.java.cn.ac.casqiem.agv.orderchain.controller;

import cn.ac.casqiem.agv.orderchain.config.RCSProperties;
import cn.ac.casqiem.agv.orderchain.dto.DispatchOrderChain;
import cn.ac.casqiem.agv.orderchain.dto.MakeOrderResponse;
import cn.ac.casqiem.agv.orderchain.dto.OrderChainRequest;
import cn.ac.casqiem.agv.orderchain.entity.OrderChainTemplate;
import cn.ac.casqiem.agv.orderchain.entity.OrderTemplate;
import cn.ac.casqiem.agv.orderchain.dto.Task;
import cn.ac.casqiem.agv.orderchain.exception.ControllerException;
import cn.ac.casqiem.agv.orderchain.exception.ControllerExceptionResponse;
import cn.ac.casqiem.agv.orderchain.exception.ResponseCodeEnum;
import cn.ac.casqiem.agv.orderchain.mapper.OrderTemplateMapper;
import cn.ac.casqiem.agv.orderchain.service.OrderChainTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dispatch/")
@Api(tags = "AGV与MES对接接口")
public class OrderChainController {
    private final Logger log = LoggerFactory.getLogger(OrderChainController.class);

    @Autowired
    private RCSProperties rcsProperties;

    @Autowired
    private OrderChainTemplateService orderChainTemplateService;

    @Resource
    private OrderTemplateMapper orderTemplateMapper;

    @ApiOperation(value = "任务下发接口", httpMethod = "POST")
    @PostMapping("/orderChain")
    public ControllerExceptionResponse update(@RequestBody OrderChainRequest orderChainRequest) {
        log.info("接收到任务请求 " + orderChainRequest.toString());
        // 获取参数信息
        List<Integer> orderChainIDList = orderChainRequest.getOrderChainIDList();
        List<String> orderChainNameList = orderChainRequest.getOrderChainNameList();
        Task task = orderChainRequest.getTask();
        // 没有task参数处理
        if (null == task) {
            task = new Task();
        }

        // 任务链ID列表和名称列表参数必须有1个且不能为空
        if ((null == orderChainIDList) && (null == orderChainNameList)) {
            return new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.ERROR_REQUEST_PARAMETER));
        } else if ((null == orderChainIDList) && (orderChainNameList.isEmpty())) {
            return new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.ERROR_REQUEST_PARAMETER_EMPTY));

        } else if ((null == orderChainNameList) && (orderChainIDList.isEmpty())) {
            return new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.ERROR_REQUEST_PARAMETER_EMPTY));
        }
        if ((null != orderChainIDList) && (null != orderChainNameList)) {
            if (orderChainIDList.isEmpty() && orderChainNameList.isEmpty()) {
                return new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.ERROR_REQUEST_PARAMETER_EMPTY));
            }
        }

        // 组装任务链列表
        List<OrderTemplate> orderTemplates = new ArrayList<>();
        if (null != orderChainNameList) {
            for (String orderChainName : orderChainNameList) {
                OrderChainTemplate orderChainTemplate = orderChainTemplateService.getOneByName(orderChainName);
                if (null == orderChainTemplate) {
                    return new ControllerExceptionResponse("E0010", "任务链名称" + orderChainName + "不存在");
                }
                List<OrderTemplate> orderList = orderTemplateMapper.getOrderListByTemplateID(orderChainTemplate.getId());
                orderTemplates.addAll(orderList);
                setTaskProperties(task, orderChainTemplate);
            }
        }

        if (null != orderChainIDList) {
            for (Integer orderChainId : orderChainIDList) {
                OrderChainTemplate orderChainTemplate = orderChainTemplateService.getById(orderChainId);
                if (null == orderChainTemplate) {
                    return new ControllerExceptionResponse("E0010", "任务链名称" + orderChainId + "不存在");
                }
                List<OrderTemplate> orderList = orderTemplateMapper.getOrderListByTemplateID(orderChainTemplate.getId());
                orderTemplates.addAll(orderList);
                setTaskProperties(task, orderChainTemplate);
            }
        }

        // POST请求参数
        DispatchOrderChain dispatchOrderChain = new DispatchOrderChain();
        dispatchOrderChain.setPo(task);
        dispatchOrderChain.setOrders(orderTemplates);
        String orderUrl = rcsProperties.getOrderUrl();
        log.info("准备下发任务 " + dispatchOrderChain.toString());

        // 发出POST请求
        Mono<ClientResponse> result = WebClient.create().post()
                .uri(orderUrl)
                .header("name", rcsProperties.getApiFrom())
                .header("token", rcsProperties.getApiToken())
                .body(Mono.just(dispatchOrderChain), DispatchOrderChain.class)
                .exchange();
        ClientResponse response = result.block();

        // 任务是否下发成功
        if (response.statusCode() == HttpStatus.OK) {
            MakeOrderResponse orderResponse = response.bodyToMono(MakeOrderResponse.class).block();
            if (orderResponse.getStatus()) {
                log.info("任务下发成功 " + orderResponse.toString());
                return new ControllerExceptionResponse(new ControllerException(ResponseCodeEnum.OK));
            }
        }
        String orderResponseStr = response.bodyToMono(String.class).block();

        return new ControllerExceptionResponse("E0011", "任务添加失败，" + orderResponseStr);
    }

    private void setTaskProperties(Task task, OrderChainTemplate orderChainTemplate) {

        if (task.getName() == null) {
            task.setName("MES运输任务");
        }
        if (task.getDomainId() == null) {
            task.setDomainId(orderChainTemplate.getDomainId());
        }
        if (task.getCycle() == null) {
            task.setCycle(orderChainTemplate.getCycle() == 1);
        }
        if (task.getPriority() == null) {
            task.setPriority(orderChainTemplate.getPriority());
        }

        if (task.getGroupId() == null) {
            task.setGroupId(orderChainTemplate.getGroupId());
        }
    }

}
