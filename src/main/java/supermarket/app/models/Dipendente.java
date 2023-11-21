package supermarket.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import supermarket.app.enums.Mansione;

@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Length(min = 2 , max = 20)
    private String nome;

    @Column
    @Length(min = 2 , max = 20)
    private String cognome;

    @Column
    @Enumerated(EnumType.STRING)
    private Mansione mansione;

    @Column
    private double stipendio;

    @ManyToOne
    @JoinColumn(name = "negozio_id")
    private Negozio negozio;

    public Dipendente() {
    }

    public Dipendente(Long id, String nome, String cognome, Mansione mansione, double stipendio, Negozio negozio) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.mansione = mansione;
        this.stipendio = stipendio;
        this.negozio = negozio;
    }

    public Dipendente(String nome, String cognome, Mansione mansione, double stipendio) {
        this.nome = nome;
        this.cognome = cognome;
        this.mansione = mansione;
        this.stipendio = stipendio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Mansione getMansione() {
        return mansione;
    }

    public void setMansione(Mansione mansione) {
        this.mansione = mansione;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

    public Negozio getNegozio() {
        return negozio;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }
}
