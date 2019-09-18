package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.or.ddit.user.model.User;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoFileException extends Exception{
		
	int a = 10/0;
	
}
