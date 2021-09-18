package com.djx.learn.feign.model.vo;

import lombok.Data;

/**
 * @author djx
 * @date 2021/8/23 下午4:39
 */
@Data
public class RouteInfoVO {


    /**
     * topi String	MQTT 主题
     */
    private String topic;

    /**
     * node	String	节点名称
     */
    private String node;

}
