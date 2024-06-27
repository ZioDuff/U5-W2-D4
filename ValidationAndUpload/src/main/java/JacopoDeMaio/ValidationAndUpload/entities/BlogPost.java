package JacopoDeMaio.ValidationAndUpload.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogPost {

    @Id
    @GeneratedValue

    private UUID id;

    private String categoria;

    private String titolo;

    private String cover;

    private String contenuto;

    @Column(name = "tempo_di_lettura")
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    public BlogPost(String categoria, String titolo,  String contenuto, int tempoDiLettura, Autore autore) {
        this.categoria = categoria;
        this.titolo = titolo;

        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autore = autore;
    }
}
