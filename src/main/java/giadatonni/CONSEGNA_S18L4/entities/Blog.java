package giadatonni.CONSEGNA_S18L4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue
    @Column(name = "blog_id")
    private UUID blogId;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String cover;
    @Column(nullable = false)
    private String contenuto;
    @Column(name = "tempo_lettura", nullable = false)
    private int tempoLettura;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User utente;

    public Blog(String categoria, String titolo, String contenuto, int tempoLettura, User utente) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        this.cover = "https://picsum.photos/200/300";
        this.utente = utente;
    }
}
