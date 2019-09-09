package com.escst.redis.controller;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.escst.commons.utils.SerializeUtil;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.vo.ReturnJson;

/**
 * @desc 
 * @author caozx
 * @date 2018年5月28日 下午5:04:59
 */
@Controller
@RequestMapping("/redis")
public class RedisController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * @desc   跳转到列表页面
	 * @return 
	 * @author caozx
	 * @date 2018年5月28日 下午5:09:22
	 */
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redis/list");
		return modelAndView;
	}
	
	/**
	 * @desc    查询所有的key
	 * @return 
	 * @author caozx
	 * @date 2018年5月28日 下午5:10:24
	 */
	@RequestMapping("/query")
	@ResponseBody
	public ReturnJson query(String key) {
		ReturnJson returnJson = null;
		try {
			if (StringUtils.isBlank(key)) {
				key = "*";
			} else {
				key += "*";
			}
			Set<String> keys = redisTemplate.keys(key);
			returnJson = ReturnJson.success(keys);
		} catch (Exception e) {
			String msg = "查询redis缓存数据异常";
			LOG.error("查询redis缓存数据异常：" + e.getMessage(),e);
			returnJson = ReturnJson.fail(msg);
		}
		return returnJson;
	}
	
	/**
	 * @desc  删除redis缓存数据
	 * @param key
	 * @return 
	 * @author caozx
	 * @date 2018年5月28日 下午5:25:05
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnJson delete(String keys) {
		ReturnJson returnJson = null;
		try {
			String[] keyArr = keys.split(",");
			for (String key : keyArr) {
				Set<String> keySet = redisTemplate.keys(key + "*");
				redisTemplate.delete(keySet);
			}
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			String msg = "删除redis缓存数据异常";
			LOG.error("删除redis缓存数据异常：" + e.getMessage(),e);
			returnJson = ReturnJson.fail(msg);
		}
		return returnJson;
	}
	
	
	
	
	/**
	 * @desc  删除redis缓存数据
	 * @param key
	 * @return 
	 * @author caozx
	 * @date 2018年5月28日 下午5:25:05
	 */
	@RequestMapping("/deleteBykey")
	@ResponseBody
	public ReturnJson deleteBykey(String key) {
		ReturnJson returnJson = null;
		try {
			Set<String> keys = redisTemplate.keys(key + "*");
			redisTemplate.delete(keys);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			String msg = "删除redis缓存数据异常";
			LOG.error("删除redis缓存数据异常：" + e.getMessage(),e);
			returnJson = ReturnJson.fail(msg);
		}
		return returnJson;
	}
	
	/**
	 * @desc  通过key查询数据
	 * @return 
	 * @author caozx
	 * @date 2018年5月28日 下午5:26:23
	 */
	@RequestMapping("/queryByKey")
	@ResponseBody
	public ReturnJson queryByKey(String key) {
		ReturnJson returnJson = null;
		try {
			byte[] value = (byte[]) redisTemplate.opsForValue().get(key);
			//反序列化从缓存中拿到的数据
            Object result = SerializeUtil.unSerialize(value);
			returnJson = ReturnJson.success(result);
		} catch (Exception e) {
			String msg = "通过key查询redis缓存数据异常";
			LOG.error("通过key查询redis缓存数据异常：" + e.getMessage(),e);
			returnJson = ReturnJson.fail(msg);
		}
		return returnJson;
	}

}
