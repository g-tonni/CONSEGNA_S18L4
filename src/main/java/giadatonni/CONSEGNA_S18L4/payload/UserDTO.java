package giadatonni.CONSEGNA_S18L4.payload;


import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserDTO (
     @NotBlank(message = "Il nome proprio deve essere inserito")
     @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra 2 e 30 caratteri")
     String nome,
     @NotBlank(message = "Il cognome proprio deve essere inserito")
     @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra 2 e 30 caratteri")
     String cognome,
     @NotBlank(message = "L'email deve essere inserita")
     @Email(message = "Indirizzo email non valido")
     String email,
     @NotNull(message = "La data deve essere inserita")
     @Past(message = "La data deve essere precedente al giorno corrente")
     LocalDate dataNascita)
{}
