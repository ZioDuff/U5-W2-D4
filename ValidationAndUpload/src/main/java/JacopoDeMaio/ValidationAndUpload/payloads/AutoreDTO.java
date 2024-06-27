package JacopoDeMaio.ValidationAndUpload.payloads;

import java.time.LocalDate;

public record AutoreDTO(
        String nome,
        String cognome,
        String email,
        LocalDate dataDiNascita
) {
}
