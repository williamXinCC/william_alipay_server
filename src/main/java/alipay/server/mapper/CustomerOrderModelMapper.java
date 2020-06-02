//package alipay.server.mapper;
//
//import com.ismarthealth.osp.core.common.pojo.to.CustomerOrderModel;
//import com.ismarthealth.osp.core.common.pojo.to.CustomerOrderModelExample;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface CustomerOrderModelMapper {
//    int countByExample(CustomerOrderModelExample example);
//
//    int deleteByExample(CustomerOrderModelExample example);
//
//    int deleteByPrimaryKey(String customerOrderId);
//
//    int insert(CustomerOrderModel record);
//
//    int insertSelective(CustomerOrderModel record);
//
//    List<CustomerOrderModel> selectByExample(CustomerOrderModelExample example);
//
//    CustomerOrderModel selectByPrimaryKey(String customerOrderId);
//
//    int updateByExampleSelective(@Param("record") CustomerOrderModel record, @Param("example") CustomerOrderModelExample example);
//
//    int updateByExample(@Param("record") CustomerOrderModel record, @Param("example") CustomerOrderModelExample example);
//
//    int updateByPrimaryKeySelective(CustomerOrderModel record);
//
//    int updateByPrimaryKey(CustomerOrderModel record);
//
//    List<CustomerOrderModel> selectPage(CustomerOrderModelExample example);
//}