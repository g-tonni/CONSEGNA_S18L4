package giadatonni.CONSEGNA_S18L4.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BlogDTO (
    @NotBlank(message = "La categoria deve essere inserita")
    @Size(min = 2, max = 30, message = "La categoria deve essere compresa tra 2 e 30 caratteri")
    String categoria,
    @NotBlank(message = "Il titolo deve essere inserito")
    @Size(min = 2, max = 30, message = "Il titolo deve essere compreso tra 2 e 30 caratteri")
    String titolo,
    @NotBlank(message = "Il contenuto deve essere inserito")
    @Size(min = 2, message = "Il contenuto deve essere almeno di 2 caratteri")
    String contenuto,
    @Min(value = 2, message = "Il tempo di lettura deve essere almeno di 2 minuti")
    int tempoLettura,
    @NotNull(message = "L'id utente deve essere inserito")
    String utenteId)
{}
