package com.djx.learn.feign.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author djx
 * @date 2021/8/9 上午10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttResponse<T> {
    /**
     * "meta": {
     * "page": 1,
     * "limit": 10000,
     * "hasnext": false,
     * "count": 0
     * },
     * "data": [],
     * "code": 0
     */
    private Integer code;

    private List<T> data;

    private PageInfo meta;


    @Data
    public static class PageInfo {
        private Integer page;
        private Integer limit;

        @JsonAlias("hasnext")
        private Boolean hasNext;
        private Integer count;
    }

    public boolean isSuccess() {
        return null != code && 0 == code;
    }


    public <R> MqttResponse<R> map(Function<? super T, ? extends R> converter) {
        return new MqttResponse<R>(this.code, this.data.stream().map(converter).collect(Collectors.toList()), meta);
    }

}
