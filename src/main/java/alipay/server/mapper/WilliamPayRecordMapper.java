package alipay.server.mapper;

import java.util.List;

import com.william.pojo.WilliamPayRecord;
import com.william.pojo.WilliamPayRecordExample;
import org.apache.ibatis.annotations.Param;

public interface WilliamPayRecordMapper {

    int countByExample(WilliamPayRecordExample example);

    int deleteByExample(WilliamPayRecordExample example);

    int deleteByPrimaryKey(String payId);

    int insert(WilliamPayRecord record);

    int insertSelective(WilliamPayRecord record);

    List<WilliamPayRecord> selectByExample(WilliamPayRecordExample example);

    WilliamPayRecord selectByPrimaryKey(String payId);

    int updateByExampleSelective(@Param("record") WilliamPayRecord record, @Param("example") WilliamPayRecordExample example);

    int updateByExample(@Param("record") WilliamPayRecord record, @Param("example") WilliamPayRecordExample example);

    int updateByPrimaryKeySelective(WilliamPayRecord record);

    int updateByPrimaryKey(WilliamPayRecord record);

    List<WilliamPayRecord> selectPage(WilliamPayRecordExample example);
}