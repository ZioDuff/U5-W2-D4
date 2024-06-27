package JacopoDeMaio.ValidationAndUpload.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record BlogPostDTO(
        @NotEmpty(message = "il campo categoria è obbligatorio, scegli una categoria! ")
        String categoria,
        @NotEmpty(message = "Il titolo è obbligatorio ")
        @Size(min = 3,max = 100,message = "il titolo deve essere compreso tra i 3 e i 100 caratteri " )
        String titolo,
        @Size(max = 200,message = "il contenuto del tuo blog-post non puo superare i 200 caretteri ")
        String contenuto,
        @NotNull(message = "il campo tempo di lettura è obbligatorio ")
        int tempoDiLettura,
        @NotNull(message = "non abbiamo trovato l'id ")
        UUID autoreId
) {
}
