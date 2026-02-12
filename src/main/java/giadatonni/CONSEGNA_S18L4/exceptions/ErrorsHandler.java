package giadatonni.CONSEGNA_S18L4.exceptions;

import giadatonni.CONSEGNA_S18L4.payload.ErrorsDTO;
import giadatonni.CONSEGNA_S18L4.payload.ErrorsListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex){
        return new ErrorsDTO(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsListDTO handleValidation(ValidationException ex){
        return new ErrorsListDTO(ex.getMessage(), ex.getErrorsList());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex){
        return new ErrorsDTO(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericServerError(Exception ex){
        ex.printStackTrace();
        return new ErrorsDTO("Errore interno, provare più tardi");
    }
}
