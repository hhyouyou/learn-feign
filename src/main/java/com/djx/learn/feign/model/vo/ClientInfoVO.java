package com.djx.learn.feign.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author djx
 * @date 2021/8/18 上午10:49
 */
@Data
public class ClientInfoVO {

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
     * data[0].username	String	客户端连接时使用的用户名
     */
    private String username;

    /**
     * data[0].proto_name	String	客户端协议名称
     */
    @JsonAlias("proto_name")
    private String protoName;

    /**
     * data[0].proto_ver	Integer	客户端使用的协议版本
     */
    @JsonAlias("proto_ver")
    private Integer protoVer;

    /**
     * data[0].ip_address	String	客户端的 IP 地址
     */
    @JsonAlias("ip_address")
    private String ipAddress;

    /**
     * data[0].port	Integer	客户端的端口
     */
    private Integer port;

    /**
     * data[0].is_bridge	Boolean	指示客户端是否通过桥接方式连接
     */
    @JsonAlias("is_bridge")
    private Boolean bridge;

    /**
     * data[0].connected	Boolean	客户端是否处于连接状态
     */
    private Boolean connected;

    /**
     * data[0].connected_at	String	客户端连接时间，格式为 "YYYY-MM-DD HH:mm:ss"
     */
    @JsonAlias("connected_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime connectedAt;

    /**
     * data[0].disconnected_at	String	客户端离线时间，格式为 "YYYY-MM-DD HH:mm:ss"， 此字段仅在 connected 为 false 时有效并被返回
     */
    @JsonAlias("disconnected_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime disconnectedAt;

    /**
     * data[0].zone	String	指示客户端使用的配置组
     */
    private String zone;

    /**
     * data[0].keepalive	Integer	保持连接时间，单位：秒
     */
    private Integer keepalive;

    /**
     * data[0].clean_start	Boolean	指示客户端是否使用了全新的会话
     */
    @JsonAlias("clean_start")
    private Boolean cleanStart;

    /**
     * data[0].expiry_interval	Integer	会话过期间隔，单位：秒
     */
    @JsonAlias("expiry_interval")
    private Integer expiryInterval;

    /**
     * data[0].created_at	String	会话创建时间，格式为 "YYYY-MM-DD HH:mm:ss"
     */
    @JsonAlias("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    /**
     * data[0].subscriptions_cnt	Integer	此客户端已建立的订阅数量
     */
    @JsonAlias("subscriptions_cnt")
    private Integer subscriptionsCnt;

    /**
     * data[0].max_subscriptions	Integer	此客户端允许建立的最大订阅数量
     */
    @JsonAlias("max_subscriptions")
    private Integer maxSubscriptions;

    /**
     * data[0].inflight	Integer	飞行队列当前长度
     */
    private Integer inflight;

    /**
     * data[0].max_inflight	Integer	飞行队列最大长度
     */
    @JsonAlias("max_inflight")
    private Integer maxInflight;

    /**
     * data[0].mqueue_len	Integer	消息队列当前长度
     */
    @JsonAlias("mqueue_len")
    private Integer queueLen;

    /**
     * data[0].max_mqueue	Integer	消息队列最大长度
     */
    @JsonAlias("max_mqueue")
    private Integer maxQueue;

    /**
     * data[0].mqueue_dropped	Integer	消息队列因超出长度而丢弃的消息数量
     */
    private Integer queueDropped;

    /**
     * data[0].awaiting_rel	Integer	未确认的 PUBREC 报文数量
     */
    @JsonAlias("awaiting_rel")
    private Integer awaitingRel;

    /**
     * data[0].max_awaiting_rel	Integer	允许存在未确认的 PUBREC 报文的最大数量
     */
    @JsonAlias("max_awaiting_rel")
    private Integer maxAwaitingRel;

    /**
     * data[0].recv_oct	Integer	EMQ X Broker（下同）接收的字节数量
     */
    @JsonAlias("recv_oct")
    private Integer recvOct;

    /**
     * data[0].recv_cnt	Integer	接收的 TCP 报文数量
     */
    @JsonAlias("recv_cnt")
    private Integer recvCnt;

    /**
     * data[0].recv_pkt	Integer	接收的 MQTT 报文数量
     */
    @JsonAlias("recv_pkt")
    private Integer recvPkt;

    /**
     * data[0].recv_msg	Integer	接收的 PUBLISH 报文数量
     */
    @JsonAlias("recv_msg")
    private Integer recvMsg;

    /**
     * data[0].send_oct	Integer	发送的字节数量
     */
    @JsonAlias("send_oct")
    private Integer sendOct;

    /**
     * data[0].send_cnt	Integer	发送的 TCP 报文数量
     */
    @JsonAlias("send_cnt")
    private Integer sendCnt;

    /**
     * data[0].send_pkt	Integer	发送的 MQTT 报文数量
     */
    @JsonAlias("send_pkt")
    private Integer sendPkt;

    /**
     * data[0].send_msg	Integer	发送的 PUBLISH 报文数量
     */
    @JsonAlias("send_msg")
    private Integer sendMsg;

    /**
     * data[0].mailbox_len	Integer	进程邮箱大小
     */
    @JsonAlias("mailbox_len")
    private Integer mailboxLen;

    /**
     * data[0].heap_size	Integer	进程堆栈大小，单位：字节
     */
    @JsonAlias("heap_size")
    private Integer heapSize;

    /**
     * data[0].reductions	Integer	Erlang reduction
     */
    @JsonAlias("reductions")
    private Integer reductions;

}
