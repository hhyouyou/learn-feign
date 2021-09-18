package com.djx.learn.feign.feign;

import com.djx.learn.feign.model.MqttResponse;
import com.djx.learn.feign.model.req.MqttPageReq;
import com.djx.learn.feign.model.req.QueryClientListReqMqtt;
import com.djx.learn.feign.model.req.QuerySubscriptionsReqMqtt;
import com.djx.learn.feign.model.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djx
 * @date 2021/8/9 上午9:51
 */
@RestController
@FeignClient(name = "mqtt-client", url = "http://10.4.17.39:8081", path = "/api/v4")
public interface MqttFeign {

    /**
     * 查询开放接口
     *
     * @return 开放api
     */
    @GetMapping
    MqttResponse<EndpointVO> getEndpoint();


    /**
     * 删除客户端
     *
     * @param clientId clientId
     * @return code 0 成功, 112 不在线
     */
    @DeleteMapping("/clients/{clientId}")
    MqttResponse<Void> deleteClient(@PathVariable String clientId);

    /**
     * 查询客户端列表
     *
     * @param req 请求参数
     * @return client 信息
     */
    @GetMapping("/clients")
    MqttResponse<ClientInfoVO> queryClientList(@SpringQueryMap QueryClientListReqMqtt req);

    /**
     * 查询订阅信息
     *
     * @param req 请求参数
     * @return client 信息
     */
    @GetMapping("/subscriptions")
    MqttResponse<SubscriptionInfoVO> querySubscriptions(@SpringQueryMap QuerySubscriptionsReqMqtt req);


    /**
     * 查询emqx状态
     * 查询emqx连接数，会话数等状态，
     *
     * @return 节点状态
     */
    @GetMapping("/stats")
    MqttResponse<NodeStatsVO> queryStats();


    /**
     * 查询节点信息
     * 查询节点cpu/内存等占用情况
     *
     * @param pageReq 分页信息
     * @return 节点信息
     */
    @GetMapping("/nodes")
    MqttResponse<NodeInfoVO> queryNodes(@SpringQueryMap MqttPageReq pageReq);

    /**
     * 查询节点信息
     * 查询节点cpu/内存等占用情况
     *
     * @param pageReq 分页信息
     * @return 节点信息
     */
    @GetMapping("/routes")
    MqttResponse<RouteInfoVO> queryRoutes(@SpringQueryMap MqttPageReq pageReq);


}
