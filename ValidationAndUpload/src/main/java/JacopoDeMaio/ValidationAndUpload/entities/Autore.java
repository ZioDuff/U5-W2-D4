package JacopoDeMaio.ValidationAndUpload.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "autori")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Autore {
    @Id
    @GeneratedValue

    private UUID id;

    private String nome;

    private String cognome;

    private String email;

    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;

    private String avatar;


    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;

    }
}
