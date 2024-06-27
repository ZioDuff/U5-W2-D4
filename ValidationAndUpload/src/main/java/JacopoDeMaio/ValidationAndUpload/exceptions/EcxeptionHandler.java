package JacopoDeMaio.ValidationAndUpload.exceptions;

import JacopoDeMaio.ValidationAndUpload.payloads.ErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EcxeptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex ){
        if(ex.getErrorList() != null){
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining());
            return new ErrorsDTO(message, LocalDateTime.now());
        }else {

        return  new ErrorsDTO(ex.getMessage(),LocalDateTime.now());
        }
    }
}
