package com.escst.commons.exception;

/**
 * @desc 业务异常处理
 * @author caozx
 * @date 2017-02-15
 */
public class EscstException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EscstException() {
		super();
	}
	
	public EscstException(String message) {
		super(message);
	}
	
	public EscstException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
