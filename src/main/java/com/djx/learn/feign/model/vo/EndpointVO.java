package com.djx.learn.feign.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author djx
 */
@Data
public class EndpointVO {
    /**
     * {
     * "path": "/acl/clientid",
     * "name": "list_clientid",
     * "method": "GET",
     * "descr": "List available mnesia in the cluster"
     * },
     */

    private String path;
    private String name;
    private String method;

    @JsonAlias("descr")
    private String desc;

}