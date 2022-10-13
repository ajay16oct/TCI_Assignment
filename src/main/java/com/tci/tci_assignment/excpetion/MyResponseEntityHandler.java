package com.tci.tci_assignment.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tci.tci_assignment.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class MyResponseEntityHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BonusException.class)
	public ResponseEntity<ErrorMessage> myBonusException(BonusException bonusException, WebRequest webRequest) {

		ErrorMessage message = new ErrorMessage(HttpStatus.NO_CONTENT, bonusException.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
	}
}
