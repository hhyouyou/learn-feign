package com.djx.learn.feign.feign;

import com.djx.learn.feign.model.MqttResponse;
import com.djx.learn.feign.model.req.MqttPageReq;
import com.djx.learn.feign.model.vo.NodeInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author djx
 * @date 2021/9/18 下午3:21
 */
@SpringBootTest
class MqttFeignTest {


    @Resource
    private MqttFeign mqttFeign;


    @Test
    void getEndpoint() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void queryClientList() {
    }

    @Test
    void querySubscriptions() {
    }

    @Test
    void queryStats() {
    }

    @Test
    void queryNodes() {
        MqttPageReq mqttPageReq = new MqttPageReq();
        MqttResponse<NodeInfoVO> nodeInfoVOMqttResponse = mqttFeign.queryNodes(mqttPageReq);
        System.out.println(nodeInfoVOMqttResponse.toString());
    }


    @Test
    void queryRoutes() {

    }
}