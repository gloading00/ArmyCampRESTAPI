package gr.marag.army.springboot.exception;

import org.springframework.http.HttpStatus;

public class ArmyAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ArmyAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ArmyAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
