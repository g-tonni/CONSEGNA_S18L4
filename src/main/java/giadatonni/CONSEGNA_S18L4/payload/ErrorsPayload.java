package giadatonni.CONSEGNA_S18L4.payload;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message){
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
