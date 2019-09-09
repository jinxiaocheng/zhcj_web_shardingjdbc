package org.tempuri;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * @desc
 * @author caozx
 * @date 2018年7月23日 下午5:50:07
 */
public class AuthHandler implements SOAPHandler<SOAPMessageContext> {
	
	private static final String NAMESPACE_URI = "http://tempuri.org/";
	
	private static final String LOCALPART = "CheckHeader";
	
	private static final String LOCALNAME = "Key";
	
	private static final String KEY = "3E5AFD9F31607CAF092EAB4A2C146C7C";

	@Override
	public void close(MessageContext context) {

	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		// 判断消息是请求还是响应
		Boolean output = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		SOAPMessage message = context.getMessage();

		if (output) {
			try {
				SOAPHeader header = message.getSOAPHeader();
				if (header == null) {
					header = message.getSOAPPart().getEnvelope().addHeader();
				}
				SOAPElement checkHeader = header.addChildElement(new QName(NAMESPACE_URI, LOCALPART));
				SOAPElement key = checkHeader.addChildElement(LOCALNAME);
				key.addTextNode(KEY);
				message.saveChanges();
			}
			catch (SOAPException e) {
				e.printStackTrace();
			}
		}

		System.out.println(output ? "客户端请求：" : "客户端接收：");
		try {
			message.writeTo(System.out);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\r\n");
		return true;

	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

}
