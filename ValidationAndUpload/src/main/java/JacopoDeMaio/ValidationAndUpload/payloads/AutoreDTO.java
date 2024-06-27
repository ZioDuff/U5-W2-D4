package JacopoDeMaio.ValidationAndUpload.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
// classe RECORD questa non fa altro che il compito del payload e ci darà solamente i Getter dei parametri che decidiamo noi
public record AutoreDTO(
//        annotazione necessaria se vogliamo che il parametro non sia mai vuoto
        @NotEmpty(message = "Il campo nome è obbligatorio")
//        annotazione che ci fa scegliere la "size" del nostro parametro
//        in tutte le annotazione e presente "message" che non è altro che il messaggio che leggeremo in caso di errore
        @Size(min = 3,max = 16, message = "Il nome deve essere compreso tra i 3 e i 16 caratteri")
        String nome,
        @NotEmpty(message = "Il campo cognome è obbligatorio")
        @Size(min = 3,max = 16, message = "Il cognome deve essere compreso tra i 3 e i 16 caratteri")
        String cognome,
        @NotEmpty(message = "L'email è un dato obbligatorio!")
        @Email(message = "L'email inserita non è valida!")
        String email,
        @NotNull(message = "il campo data di nascita è obbligatorio ")
        LocalDate dataDiNascita
) {
}
