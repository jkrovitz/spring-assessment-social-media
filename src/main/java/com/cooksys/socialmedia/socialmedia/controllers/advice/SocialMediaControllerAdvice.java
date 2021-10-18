package com.cooksys.socialmedia.socialmedia.controllers.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksys.socialmedia.socialmedia.dtos.ErrorDto;
import com.cooksys.socialmedia.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;

@ControllerAdvice(basePackages = { "com.cooksys.socialmedia.socialmedia.controllers" })
@ResponseBody
public class SocialMediaControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
		return new ErrorDto(badRequestException.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
		return new ErrorDto(notFoundException.getMessage());
	}
}
