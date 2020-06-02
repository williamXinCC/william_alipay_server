package alipay.server.service;

import alipay.server.common.vo.AlipayCallbackVO;

public interface CallbackService {
    void callback(AlipayCallbackVO alipayCallbackVO);
}
