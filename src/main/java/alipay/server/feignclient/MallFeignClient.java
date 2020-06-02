package alipay.server.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author: HMG
 * @Date 2018/8/6
 * @Description: 文件server
 */
@FeignClient("mall-server")
public interface MallFeignClient {

    @Async
    @RequestMapping(value = "/customerEquity/binding", method = RequestMethod.POST)
    String binding(@RequestParam("orderId") String orderId, @RequestParam("isSendMsg") Integer isSendMsg);

}
