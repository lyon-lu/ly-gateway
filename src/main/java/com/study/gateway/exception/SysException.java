package com.study.gateway.exception;

public class SysException extends RuntimeException {
	private static final long serialVersionUID = 1429956030966288710L;
	private Object[] params;

	public SysException() {
	}

	public SysException(String message) {
		super(message);
	}

	public SysException(Throwable cause) {
		super(cause);
	}

	public SysException(String message, Object[] params) {
		super(message);
		this.params = params;
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
	}

	public SysException(String message, Object[] params, Throwable cause) {
		super(message, cause);
		this.params = params;
	}

	public Object[] getParams() {
		return this.params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
}