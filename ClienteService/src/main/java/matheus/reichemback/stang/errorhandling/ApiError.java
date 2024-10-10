package matheus.reichemback.stang.errorhandling;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
	private HttpStatus status;
	
	private String message;
	
	private String debugMessage;
	
	public ApiError() {}
	
	public ApiError(HttpStatus status) {
		this.status = status;
	}
	
	public ApiError(HttpStatus status, Throwable ex) {
		this.status = status;
		this.message = "Unexpected_Error";
		this.debugMessage = ex.getMessage();
	}
	
	public ApiError(HttpStatus status,String message, Throwable ex) {
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getMessage();
	}
}
