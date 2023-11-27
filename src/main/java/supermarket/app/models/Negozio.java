package supermarket.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder;

@Entity
@Table(name = "negozi")
public class Negozio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Length(min = 2, max = 20)
    @NotNull
    private String citta;

    @Column
    @Length(min = 2, max = 40)
    @NotNull
    private String indirizzo;

    @Column(unique = true)
    @Length(min = 2, max = 50)
    @NotNull
    private String pIva;

    public Negozio() {
    }

    public Negozio(Long id, String citta, String indirizzo, String pIva) {
        this.id = id;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }
}
