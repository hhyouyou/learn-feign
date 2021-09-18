package com.djx.learn.feign.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author djx
 * @date 2021/8/23 下午3:11
 */
@Data
public class NodeStatsVO {

    private String node;

    private Stats stats;


    @Data
    public static class Stats {

        /**
         * connections.count	Integer	当前连接数量
         */
        @JsonAlias("connections.count")
        private Integer connectionsCount;

        /**
         * connections.max	Integer	连接数量的历史最大值
         */
        @JsonAlias("connections.max")
        private Integer connectionsMax;

        /**
         * channels.count	Integer	即 sessions.count
         */
        @JsonAlias("channels.count")
        private Integer channelsCount;

        /**
         * channels.max	Integer	即 session.max
         */
        @JsonAlias("channels.max")
        private Integer channelsMax;

        /**
         * sessions.count	Integer	当前会话数量
         */
        @JsonAlias("sessions.count")
        private Integer sessionsCount;

        /**
         * sessions.max	Integer	会话数量的历史最大值
         */
        @JsonAlias("sessions.max")
        private Integer sessionsMax;

        /**
         * topics.count	Integer	当前主题数量
         */
        @JsonAlias("topics.count")
        private Integer topicsCount;

        /**
         * topics.max	Integer	主题数量的历史最大值
         */
        @JsonAlias("topics.max")
        private Integer topicsMax;

        /**
         * suboptions.count	Integer	即 subscriptions.count
         * subscriptions.count	Integer	当前订阅数量，包含共享订阅
         */
        @JsonAlias("subscriptions.count")
        private Integer subscriptionsCount;

        /**
         * suboptions.max	Integer	即 subscriptions.max
         * subscriptions.max	Integer	订阅数量的历史最大值
         */
        @JsonAlias("subscriptions.max")
        private Integer subscriptionsMax;

        /**
         * subscribers.count	Integer	当前订阅者数量
         */
        @JsonAlias("subscribers.count")
        private Integer subscribersCount;

        /**
         * subscribers.max	Integer	订阅者数量的历史最大值
         */
        @JsonAlias("subscribers.max")
        private Integer subscribersMax;

        /**
         * subscriptions.shared.count	Integer	当前共享订阅数量
         */
        @JsonAlias("subscriptions.shared.count")
        private Integer subscriptionsSharedCount;

        /**
         * subscriptions.shared.max	Integer	共享订阅数量的历史最大值
         */
        @JsonAlias("subscriptions.shared.max")
        private Integer subscriptionsSharedMax;

        /**
         * routes.count	Integer	当前路由数量
         */
        @JsonAlias("routes.count")
        private Integer routesCount;

        /**
         * routes.max	Integer	路由数量的历史最大值
         */
        @JsonAlias("routes.max")
        private Integer routesMax;

        /**
         * retained.count	Integer	当前保留消息数量
         */
        @JsonAlias("retained.count")
        private Integer retainedCount;

        /**
         * retained.max	Integer	保留消息的历史最大值
         */
        @JsonAlias("retained.max")
        private Integer retainedMax;


    }
}
