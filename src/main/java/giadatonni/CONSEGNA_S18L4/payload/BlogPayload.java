package giadatonni.CONSEGNA_S18L4.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BlogPayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoLettura;
    private String utenteId;
}
