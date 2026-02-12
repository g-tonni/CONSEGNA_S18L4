package giadatonni.CONSEGNA_S18L4.payload;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO (
        String message,
        LocalDateTime timestamp,
        List<String> errorsList
)
{
    public ErrorsListDTO(String message, List<String> errorsList) {
        this(message, LocalDateTime.now(), errorsList);
    }
}
