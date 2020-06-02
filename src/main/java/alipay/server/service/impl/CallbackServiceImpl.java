package alipay.server.service.impl;

import alipay.server.common.vo.AlipayCallbackVO;
import alipay.server.mapper.*;
import alipay.server.service.CallbackService;
import com.william.ali.AlipayPayRecord;
import com.william.ali.AlipayPayRecordExample;
import com.william.pojo.WilliamPayRecord;
import com.william.pojo.WilliamPayRecordExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class CallbackServiceImpl implements CallbackService {

    private Logger callbackLogger = LoggerFactory.getLogger("callbackLog");
    private Logger logger = LoggerFactory.getLogger(CallbackServiceImpl.class);
    @Autowired
    AlipayPayRecordMapper alipayRecordMapper;
    @Autowired
    WilliamPayRecordMapper payRecordModelMapper;

    @Override
    public void callback(AlipayCallbackVO alipayCallbackVO) {
        // 判断是否已经通知过了
        boolean hadNotify = verifyHadNotify(alipayCallbackVO.getOut_trade_no());
        if (hadNotify){
            callbackLogger.error("支付宝外部订单号已经通知: " + alipayCallbackVO.getOut_trade_no());
            return;
        }
        // 保存回调记录
        Integer alipayId = insertCallbackRecord(alipayCallbackVO);

        // 一下交给对应的业务去处理
        // 获取支付记录
//        WilliamPayRecord payRecordModel = getWilliamPayRecordByOutTradeNo(alipayCallbackVO.getOut_trade_no());
//        if (payRecordModel != null) {
//            alipayCallbackVO.setPayId(payRecordModel.getPayId());
//            alipayCallbackVO.setOrderId(payRecordModel.getOrderId());
//        }
//        // 修改订单支付结果和支付记录
//        if (payRecordModel != null && StringUtils.isNotBlank(payRecordModel.getPayId())) {
//            // 修改支付记录状态
//            WilliamPayRecord updateWilliamPayRecord = new WilliamPayRecord();
//            updateWilliamPayRecord.setPayStatus(PayStatusEnum.PAY.getIndex());
//            updateWilliamPayRecord.setPayId(payRecordModel.getPayId());
//            updateWilliamPayRecord.setRefId(alipayId);
//            updateWilliamPayRecord.setPayTime(new Date());
//            payRecordModelMapper.updateByPrimaryKeySelective(updateWilliamPayRecord);
//            // 获取订单信息
//            CustomerOrderModel oldCustomerOrderModel = customerOrderModelMapper.selectByPrimaryKey(payRecordModel.getOrderId());
//            // 修改订单状态
//            CustomerOrderModel updateCustomerOrderModel = new CustomerOrderModel();
//            updateCustomerOrderModel.setCustomerOrderId(payRecordModel.getOrderId());
//            // 判断是实物订单还是虚拟订单
//            if (GoodsTypeEnumStatusEnum.VIRTUAL_GOODS.getIndex() == oldCustomerOrderModel.getOrderGoodsType()) {
//                // 虚拟订单设置订单状态为已支付
//                updateCustomerOrderModel.setOrderStatus(GoodsOrderStatusEnum.PAY.getIndex());
//            } else if (GoodsTypeEnumStatusEnum.PHYSICAL_GOODS.getIndex() == oldCustomerOrderModel.getOrderGoodsType()) {
//                // 实物订单设置订单状态为待发货
//                updateCustomerOrderModel.setOrderStatus(GoodsOrderStatusEnum.WAIT_EMS.getIndex());
//            } else {
//                // 订单类型不正确设置订单状态为已支付
//                logger.error("订单类型不正确: alipayCallbackVO:" + alipayCallbackVO.toString() + ",payRecordModel:" + payRecordModel.toString());
//                updateCustomerOrderModel.setOrderStatus(GoodsOrderStatusEnum.PAY.getIndex());
//            }
//            updateCustomerOrderModel.setPayId(payRecordModel.getPayId());
//            updateCustomerOrderModel.setPayChannelId(payRecordModel.getPayChannelId());
//            updateCustomerOrderModel.setPayTime(new Date());
//            customerOrderModelMapper.updateByPrimaryKeySelective(updateCustomerOrderModel);
//            // 加权益
//            ospFeignClient.binding(payRecordModel.getOrderId(), 0);
//        }
    }

    /**
     * @Author: HMG
     * @Date: 2019/10/9
     * @Description: 根据外部订单号获取支付记录
     */
    private WilliamPayRecord getWilliamPayRecordByOutTradeNo(String outTradeNo) {
        WilliamPayRecord payRecordModel = null;
        WilliamPayRecordExample payRecordModelExample = new WilliamPayRecordExample();
        WilliamPayRecordExample.Criteria criteria = payRecordModelExample.createCriteria();
        criteria.andOutTradeIdEqualTo(outTradeNo);
        List<WilliamPayRecord> list = payRecordModelMapper.selectByExample(payRecordModelExample);
        if (!CollectionUtils.isEmpty(list)) {
            payRecordModel = list.get(0);
        }
        return payRecordModel;
    }

    /**
     * @Author: HMG
     * @Date: 2018/12/8
     * @Description: 验证外部订单号是否已经通知过了
     */
    private boolean verifyHadNotify(String outTradeNo) {
        AlipayPayRecordExample alipayWilliamPayRecordExample = new AlipayPayRecordExample();
        AlipayPayRecordExample.Criteria criteria = alipayWilliamPayRecordExample.createCriteria();
        criteria.andOutTradeNoEqualTo(outTradeNo);
        alipayWilliamPayRecordExample.setPageSize(0);
        alipayWilliamPayRecordExample.setPageSize(5);
        List<AlipayPayRecord> alipayPayRecordList = alipayRecordMapper.selectByExample(alipayWilliamPayRecordExample);
        if(!CollectionUtils.isEmpty(alipayPayRecordList)){
            return true;
        }
        return false;
    }

    /**
     * @Author: HMG
     * @Date: 2018/12/6
     * @Description: 插入支付宝回调记录
     */
    private Integer insertCallbackRecord(AlipayCallbackVO alipayCallbackVO) {
         AlipayPayRecord alipayWilliamPayRecord = new  AlipayPayRecord();
        alipayWilliamPayRecord.setNotifyTime(alipayCallbackVO.getNotify_time());
        alipayWilliamPayRecord.setPayId(alipayCallbackVO.getPayId());
        alipayWilliamPayRecord.setOrderId(alipayCallbackVO.getOrderId());
        alipayWilliamPayRecord.setNotifyType(alipayCallbackVO.getNotify_type());
        alipayWilliamPayRecord.setNotifyId(alipayCallbackVO.getNotify_id());
        alipayWilliamPayRecord.setSign(alipayCallbackVO.getSign());
        alipayWilliamPayRecord.setTradeNo(alipayCallbackVO.getTrade_no());
        alipayWilliamPayRecord.setOutTradeNo(alipayCallbackVO.getOut_trade_no());
        alipayWilliamPayRecord.setOutBizNo(alipayCallbackVO.getOut_biz_no());
        alipayWilliamPayRecord.setBuyerId(alipayCallbackVO.getBuyer_id());
        alipayWilliamPayRecord.setBuyerLogonId(alipayCallbackVO.getBuyer_logon_id());
        alipayWilliamPayRecord.setSellerId(alipayCallbackVO.getSeller_id());
        alipayWilliamPayRecord.setSellerEmail(alipayCallbackVO.getSeller_email());
        alipayWilliamPayRecord.setTradeStatus(alipayCallbackVO.getTrade_status());
        alipayWilliamPayRecord.setTotalAmount(alipayCallbackVO.getTotal_amount());
        alipayWilliamPayRecord.setReceiptAmount(alipayCallbackVO.getReceipt_amount());
        alipayWilliamPayRecord.setInvoiceAmount(alipayCallbackVO.getInvoice_amount());
        alipayWilliamPayRecord.setBuyerPayAmount(alipayCallbackVO.getBuyer_pay_amount());
        alipayWilliamPayRecord.setRefundFee(alipayCallbackVO.getRefund_fee());
        alipayWilliamPayRecord.setSubject(alipayCallbackVO.getSubject());
        alipayWilliamPayRecord.setBody(alipayCallbackVO.getBody());
        alipayWilliamPayRecord.setGmtCreate(alipayCallbackVO.getGmt_create());
        alipayWilliamPayRecord.setGmtPayment(alipayCallbackVO.getGmt_payment());
        alipayWilliamPayRecord.setGmtRefund(alipayCallbackVO.getGmt_refund());
        alipayWilliamPayRecord.setGmtClose(alipayCallbackVO.getGmt_close());
        alipayWilliamPayRecord.setFundBillList(alipayCallbackVO.getFund_bill_list());
        alipayWilliamPayRecord.setPassbackParams(alipayCallbackVO.getPassback_params());
        alipayWilliamPayRecord.setCreateTime(new Date());
        alipayRecordMapper.addAlipayPayRecord(alipayWilliamPayRecord);
        return alipayWilliamPayRecord.getAlipayId();
    }
}
