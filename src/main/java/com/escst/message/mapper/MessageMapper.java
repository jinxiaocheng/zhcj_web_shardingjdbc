package com.escst.message.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.mapper.BaseMapper;
import com.escst.message.entity.MessageEntity;
import org.springframework.stereotype.Repository;

/**
 * @desc
 * @author niejing
 * @date 2018年2月6日 下午2:56:19
 */
@Repository
public interface MessageMapper {

	List<Map<String, Object>> selectMapList(MessageEntity entity);

	List<String> selectByBillId(String billId);

	MessageEntity selectByIdDetail(String id);

	int selectCount(Map<String,Object> map);

	void batchUpdate(List<String> list);
}
