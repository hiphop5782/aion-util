package com.hacademy.macro.exception;

public class KeyException extends RuntimeException {
	
	public KeyException(String str) {
		super(str);
	}
	
	@Override
	public String getMessage() {
		return "키 변환 실패 : " + super.getMessage();
	}
	
}
