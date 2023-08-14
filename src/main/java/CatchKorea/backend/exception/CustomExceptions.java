package CatchKorea.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class CustomExceptions{
    @AllArgsConstructor
    @Getter
    public static class CustomException extends RuntimeException  {
        private HttpStatus httpStatus;
        private String info;
    }
}
