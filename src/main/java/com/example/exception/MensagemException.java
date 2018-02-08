package com.example.exception;

public class MensagemException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public MensagemException(String message) {
        super(message);
    }

}
