package com.escst.thirdPart.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.tempuri.AuthHandler;
import org.tempuri.ItemInfoService;

/**
 * @desc 获取第三方service
 * @author niejing
 * @date 2018年7月24日 下午1:53:41
 */
public class ThirdPartService {

	public static ItemInfoService getItemInfoService(){
		ItemInfoService it = new ItemInfoService();
		it.setHandlerResolver(new HandlerResolver() {
			public List<Handler> getHandlerChain(PortInfo portInfo) {
				List<Handler> handlerChain = new ArrayList<Handler>();
				handlerChain.add(new AuthHandler());
				return handlerChain;
			}
		});

		return it;
	}
}
