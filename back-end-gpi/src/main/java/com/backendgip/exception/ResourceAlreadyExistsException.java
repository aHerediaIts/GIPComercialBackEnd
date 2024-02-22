//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class ResourceAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
