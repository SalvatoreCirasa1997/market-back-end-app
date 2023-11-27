package supermarket.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import supermarket.app.enums.MansioneEnum;

@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Length(min = 2 , max = 20)
    @NotNull
    private String nome;

    @Column
    @Length(min = 2 , max = 20)
    @NotNull
    private String cognome;

    @Column
    @Enumerated(EnumType.STRING)
    private MansioneEnum mansioneEnum;

    @Column
    @NotNull
    private double stipendio;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "negozio_id")
    private Negozio negozio;

    public Dipendente() {
    }

    public Dipendente(Long id, String nome, String cognome, MansioneEnum mansioneEnum, double stipendio, Negozio negozio) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.mansioneEnum = mansioneEnum;
        this.stipendio = stipendio;
        this.negozio = negozio;
    }

    public Dipendente(String nome, String cognome, MansioneEnum mansioneEnum, double stipendio) {
        this.nome = nome;
        this.cognome = cognome;
        this.mansioneEnum = mansioneEnum;
        this.stipendio = stipendio;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MansioneEnum getMansione() {
        return mansioneEnum;
    }

    public void setMansione(MansioneEnum mansioneEnum) {
        this.mansioneEnum = mansioneEnum;
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
