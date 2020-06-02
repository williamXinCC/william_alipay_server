package alipay.server.mapper;

import java.util.List;

import com.william.ali.AlipayPayRecord;
import com.william.ali.AlipayPayRecordExample;
import org.apache.ibatis.annotations.Param;

public interface AlipayPayRecordMapper {

    Integer addAlipayPayRecord(AlipayPayRecord record);

    int countByExample(AlipayPayRecordExample example);

    int deleteByExample(AlipayPayRecordExample example);

    int deleteByPrimaryKey(Integer alipayId);

    int insert(AlipayPayRecord record);

    int insertSelective(AlipayPayRecord record);

    List<AlipayPayRecord> selectByExample(AlipayPayRecordExample example);

    AlipayPayRecord selectByPrimaryKey(Integer alipayId);

    int updateByExampleSelective(@Param("record") AlipayPayRecord record, @Param("example") AlipayPayRecordExample example);

    int updateByExample(@Param("record") AlipayPayRecord record, @Param("example") AlipayPayRecordExample example);

    int updateByPrimaryKeySelective(AlipayPayRecord record);

    int updateByPrimaryKey(AlipayPayRecord record);

    List<AlipayPayRecord> selectPage(AlipayPayRecordExample example);
}