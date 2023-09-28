package com.turkcell.spring.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@SpringBootApplication
public class Application {

	public static void main(String[] args) { SpringApplication.run(Application.class, args);}

	// Uygulamanın neresinde olursa olsun runtime exceptionlarını yaklamak için kullandığımız yöntem...
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public List<String> handleValidationException(MethodArgumentNotValidException ex){
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			String message = fieldName + ":" + errorMessage;
			errors.add(message);
		});
		return errors;
	}
}


