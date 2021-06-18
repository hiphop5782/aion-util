package com.hacademy.macro.exception;

public class KeyException extends RuntimeException {
	
	@Override
	public String getMessage() {
		return "키 변환 실패";
	}
	
}
