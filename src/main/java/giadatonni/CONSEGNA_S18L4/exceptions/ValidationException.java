package giadatonni.CONSEGNA_S18L4.exceptions;

import lombok.AllArgsConstructor;

import java.util.List;
public class ValidationException extends RuntimeException {

    private List<String> errorsList;

    public ValidationException(List<String> errors) {
        super("Errore nella validazione");
        this.errorsList = errors;
    }
}
