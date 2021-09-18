package com.djx.learn.feign.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author djx
 * @date 2021/9/3 上午11:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MqttPageReq {
    /**
     * _page	Integer	False	1	页码
     */
    @JsonProperty("_page")
    private Integer page;

    /**
     * _limit	Integer	False	10000	每页显示的数据条数，未指定时由 emqx-management 插件的配置项 max_row_limit 决定
     */
    @JsonProperty("_limit")
    private Integer limit;
}
