package giadatonni.CONSEGNA_S18L4.payload;


import java.time.LocalDateTime;

public record ErrorsDTO(
     String message,
     LocalDateTime timestamp)
{
    public ErrorsDTO(String message) {
        this(message, LocalDateTime.now());
    }
}
