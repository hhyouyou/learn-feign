package com.djx.learn.feign.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author djx
 * @date 2021/8/23 下午4:39
 */
@Data
public class NodeInfoVO {


    /**
     * connections	Integer	当前接入此节点的客户端数量
     */
    private Integer connections;

    /**
     * load1	String	1 分钟内的 CPU 平均负载
     */
    private String load1;

    /**
     * load5	String	5 分钟内的 CPU 平均负载
     */
    private String load5;

    /**
     * load15	String	15 分钟内的 CPU 平均负载
     */
    private String load15;

    /**
     * max_fds	Integer	操作系统的最大文件描述符限制
     */
    @JsonAlias("max_fds")
    private Integer maxFds;

    /**
     * memory_total	String	VM 已分配的系统内存
     */
    @JsonAlias("memory_total")
    private String memoryTotal;

    /**
     * memory_used	String	VM 已占用的内存大小
     */
    @JsonAlias("memory_used")
    private String memoryUsed;

    /**
     * node	String	节点名称
     */
    private String node;

    /**
     * node_status	String	节点状态
     */
    @JsonAlias("node_status")
    private String nodeStatus;

    /**
     * otp_release	String	EMQ X 使用的 Erlang/OTP 版本
     */
    @JsonAlias("otp_release")
    private String otpRelease;

    /**
     * process_available	Integer	可用的进程数量
     */
    @JsonAlias("process_available")
    private Integer processAvailable;

    /**
     * process_used	Integer	已占用的进程数量
     */
    @JsonAlias("process_used")
    private Integer processUsed;

    /**
     * uptime	String	EMQ X 运行时间
     */
    private String uptime;

    /**
     * version	String	EMQ X 版本
     */
    private String version;

}
