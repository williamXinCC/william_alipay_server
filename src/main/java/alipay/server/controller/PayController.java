package alipay.server.controller;

import alipay.server.common.constant.Constant;
import alipay.server.common.vo.AlipayPayVo;
import alipay.server.common.vo.AlipayRefundRequestVo;
import com.alipay.api.AlipayApiException;
import alipay.server.utils.alipay.AlipayUtil;
import com.william.pojo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/william-alipay")
public class PayController {

    @ApiOperation(value = "支付宝H5支付[HMG]", notes = "支付宝支付[HMG]")
    @RequestMapping(value = "/pay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result pay(@RequestBody AlipayPayVo alipayPayVo) {
        String result = AlipayUtil.getInstance().pay(alipayPayVo.getOutTradeNo(), alipayPayVo.getSubject(), alipayPayVo.getTotalAmount(), alipayPayVo.getBody(),alipayPayVo.getReturnUrl());
        return new Result(Constant.SUCCESS, result, "", "");
    }

    @ApiOperation(value = "支付宝退款[HMG]", notes = "支付宝支付[HMG]")
    @RequestMapping(value = "/refund", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result refund(@RequestBody AlipayRefundRequestVo alipayRefundRequestVo) {
        AlipayUtil.getInstance().refund(alipayRefundRequestVo);
        return new Result(Constant.SUCCESS, "退款成功");
    }

    @ApiOperation(value = "支付宝APP支付[HMG]", notes = "支付宝支付[HMG]")
    @RequestMapping(value = "/appPay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result appPay(@RequestBody AlipayPayVo alipayPayVo) {
        String result = AlipayUtil.getInstance().appPay(alipayPayVo.getOutTradeNo(), alipayPayVo.getSubject(), alipayPayVo.getTotalAmount(), alipayPayVo.getBody(),alipayPayVo.getReturnUrl());
        return new Result(Constant.SUCCESS, result, "", "");
    }

    @ApiOperation(value = "查询支付宝支付结果[HMG]", notes = "查询支付宝支付结果[HMG]")
    @RequestMapping(value = "/getPayStatus", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result getPayStatus(@RequestParam String outTradeNo) {
        boolean result = AlipayUtil.getInstance().getPayStatus(outTradeNo);
        return new Result(Constant.SUCCESS, result ? 1 : 0);
    }

    @ApiOperation(value = "支付宝验签[HMG]", notes = "支付宝验签[HMG]")
    @RequestMapping(value = "/veritySign", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result veritySign(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        boolean result = false;
        try {
            result = AlipayUtil.getInstance().verifyCheck(params);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return new Result(Constant.SUCCESS, result ? 1 : 0);
    }

}
