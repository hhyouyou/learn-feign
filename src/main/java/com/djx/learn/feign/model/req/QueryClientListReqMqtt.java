package com.djx.learn.feign.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author djx
 * @date 2021/8/18 上午10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QueryClientListReqMqtt extends MqttPageReq {

    /**
     * _page	Integer	False	1	页码
     * _limit	Integer	False	10000	每页显示的数据条数，未指定时由 emqx-management 插件的配置项 max_row_limit 决定
     * <p>
     * clientid	String	False	客户端标识符
     * username	String	False	客户端用户名
     * zone	String	False	客户端配置组名称
     * ip_address	String	False	客户端 IP 地址
     * conn_state	Enum	False	客户端当前连接状态， 可取值有：connected,idle,disconnected
     * clean_start	Bool	False	客户端是否使用了全新的会话
     * proto_name	Enum	False	客户端协议名称，可取值有：MQTT,CoAP,LwM2M,MQTT-SN
     * proto_ver	Integer	False	客户端协议版本
     * _like_clientid	String	False	客户端标识符，子串方式模糊查找
     * _like_username	String	False	客户端用户名，子串方式模糊查找
     * _gte_created_at	Integer	False	客户端会话创建时间，小于等于查找
     * _lte_created_at	Integer	False	客户端会话创建时间，大于等于查找
     * _gte_connected_at	Integer	False	客户端连接创建时间，小于等于查找
     * _lte_connected_at	Integer	False	客户端连接创建时间，大于等于查找
     */


    /**
     * clientid	String	False	客户端标识符
     */
    @JsonProperty("clientid")
    private String clientId;

    /**
     * username	String	False	客户端用户名
     */
    private String username;

    /**
     * zone	String	False	客户端配置组名称
     */
    private String zone;

    /**
     * ip_address	String	False	客户端 IP 地址
     */
    @JsonProperty("ip_address")
    private String ipAddress;

    /**
     * conn_state	Enum	False	客户端当前连接状态， 可取值有：connected,idle,disconnected
     */
    @JsonProperty("conn_state")
    private String connState;

    /**
     * clean_start	Bool	False	客户端是否使用了全新的会话
     */
    @JsonProperty("clean_start")
    private Boolean cleanStart;

    /**
     * proto_name	Enum	False	客户端协议名称，可取值有：MQTT,CoAP,LwM2M,MQTT-SN
     */
    @JsonProperty("proto_name")
    private String protoName;

    /**
     * proto_ver   Integer	False	客户端协议版本
     */
    @JsonProperty("proto_ver")
    private Integer protoVer;

    /**
     * _like_clientid	String	False	客户端标识符，子串方式模糊查找
     */
    @JsonProperty("_like_clientId")
    private String likeClientId;

    /**
     * _like_username	String	False	客户端用户名，子串方式模糊查找
     */
    @JsonProperty("_like_username")
    private String likeUsername;
    /**
     * _gte_created_at	Integer	False	客户端会话创建时间，小于等于查找
     */
    @JsonProperty("_gte_created_at")
    private Integer gteCreatedAt;

    /**
     * _lte_created_at	Integer	False	客户端会话创建时间，大于等于查找
     */
    @JsonProperty("_lte_created_at")
    private Integer lteCreatedAt;

    /**
     * _gte_connected_at	Integer	False	客户端连接创建时间，小于等于查找
     */
    @JsonProperty("_gte_connected_at")
    private Integer gteConnectedAt;

    /**
     * _lte_connected_at	Integer	False	客户端连接创建时间，大于等于查找
     */
    @JsonProperty("_lte_connected_at")
    private Integer lteConnectedAt;


}
