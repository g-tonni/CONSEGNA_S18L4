package giadatonni.CONSEGNA_S18L4.payload;


import java.time.LocalDate;

public record UserDTO (
     String nome,
     String cognome,
     String email,
     LocalDate dataNascita)
{}
