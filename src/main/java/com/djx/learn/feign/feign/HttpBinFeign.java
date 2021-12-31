package com.djx.learn.feign.feign;

import com.djx.learn.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author djx
 * @date 2021/12/29 下午7:20
 */
@FeignClient(name = "httpBin", url = "https://httpbin.org")
public interface HttpBinFeign {

    @GetMapping("/ip")
    Object getIp();

}
