package com.djx.learn.feign.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author djx
 * @date 2021/8/18 上午10:49
 */
@Data
public class SubscriptionInfoVO {

    /**
     * data[0].node	String	客户端所连接的节点名称
     */
    private String node;

    /**
     * data[0].clientid	String	客户端标识符
     */
    @JsonAlias("clientid")
    private String clientId;

    /**
     * data[0].topic	String	订阅主题
     */
    private String topic;

    /**
     * data[0].qos	Integer	QoS 等级
     */
    private Integer qos;

}
