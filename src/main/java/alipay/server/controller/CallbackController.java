package alipay.server.controller;

import com.alipay.api.AlipayApiException;
import alipay.server.common.vo.AlipayCallbackVO;
import alipay.server.service.CallbackService;
import alipay.server.utils.alipay.AlipayUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/callback")
public class CallbackController {

    private Logger errorLogger = LoggerFactory.getLogger("errorLog");
    private Logger callbackLogger = LoggerFactory.getLogger("callbackLog");

    @Autowired
    CallbackService callbackService;

    @ApiOperation(value = "支付宝回调[HMG]", notes = "支付宝回调[HMG]")
    @RequestMapping(value = "/callback", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String callback(HttpServletRequest request, AlipayCallbackVO alipayCallbackVO) {
        // 把请求参数组装成Map<String,String>
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        callbackLogger.error("callback params:" + params.toString());
        // 验签
        boolean verifyResult;
        try {
            verifyResult = AlipayUtil.getInstance().verifyCheck(params);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            verifyResult =false;
        }
        if (verifyResult == false) {
            errorLogger.error("验签失败 params:" + params.toString());
            callbackLogger.error(" 验签失败 params:" + params.toString());
            return "error";
        }
        // 只有交易状态为交易成功(TRADE_SUCCESS)时处理,交易完结(TRADE_FINISHED),交易关闭(TRADE_CLOSED)不处理
        if(alipayCallbackVO.getTrade_status().equals("TRADE_SUCCESS")){
            // 处理业务逻辑
            try {
                callbackService.callback(alipayCallbackVO);
            } catch (Exception e) {
                e.printStackTrace();
                errorLogger.error("errorMessage", e);
                return "error";
            }
        }
        return "success";
    }
}
