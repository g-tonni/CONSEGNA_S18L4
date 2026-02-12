package giadatonni.CONSEGNA_S18L4.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserPayload {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
}
