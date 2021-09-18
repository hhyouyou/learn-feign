package com.djx.learn.feign.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author djx
 * @date 2021/8/18 上午10:13
 */
@Data
public class QuerySubscriptionsReqMqtt extends MqttPageReq {


    /**
     * clientid	String	False	客户端标识符
     */
    @JsonProperty("clientid")
    private String clientId;

    /**
     * topic	String	主题，全等查询
     */
    private String topic;

    /**
     * qos	Enum	可取值为：0,1,2
     */
    private Integer qos;

    /**
     * share	String	共享订阅的组名称
     */
    private String share;

    /**
     * _match_topic   Integer	False	客户端协议版本
     */
    @JsonProperty("_match_topic")
    private String matchTopic;

}
