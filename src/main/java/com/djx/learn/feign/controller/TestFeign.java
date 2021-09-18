package com.djx.learn.feign.controller;

import com.djx.learn.feign.config.FeignConfig;
import com.djx.learn.feign.config.TestRegistrar;
import com.djx.learn.feign.feign.MqttFeign;
import com.djx.learn.feign.model.MqttResponse;
import com.djx.learn.feign.model.req.MqttPageReq;
import com.djx.learn.feign.model.vo.NodeInfoVO;
import com.djx.learn.feign.model.vo.RouteInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author djx
 * @date 2021/9/18 下午3:41
 */
@RestController
@RequestMapping("/test")
public class TestFeign {

    @Resource
    private MqttFeign mqttFeign;

    @Resource
    private FeignConfig feignConfig;

//    @Resource
//    private TestRegistrar testRegistrar;

    /**
     * 查询节点信息
     * 查询节点cpu/内存等占用情况
     *
     * @param pageReq 分页信息
     * @return 节点信息
     */
    @GetMapping("/nodes")
    public MqttResponse<NodeInfoVO> queryNodes(MqttPageReq pageReq) {

        return mqttFeign.queryNodes(pageReq);
    }

    /**
     * 查询节点信息
     * 查询节点cpu/内存等占用情况
     *
     * @param pageReq 分页信息
     * @return 节点信息
     */
    @GetMapping("/routes")
    public MqttResponse<RouteInfoVO> queryRoutes(MqttPageReq pageReq) {

        return mqttFeign.queryRoutes(pageReq);
    }


}
