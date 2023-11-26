package supermarket.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import supermarket.app.enums.TipologiaFornituraEnum;

@Entity
@Table(name = "Rifornitore")
public class Rifornitore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "devi per forza inserire il tuo nome")
    private String nome;

    @NotBlank(message = "devi per forza inserire la partita Iva")
    @Pattern(regexp = "^(IT)?[0-9]{11}$", message = "Partita IVA non valida")
    private String partitaIva;

    @Enumerated(EnumType.STRING)
    private TipologiaFornituraEnum tipologiaFornituraEnum;

    public Rifornitore(long id, String nome, String partitaIva, TipologiaFornituraEnum tipologiaFornituraEnum) {
        this.id = id;
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.tipologiaFornituraEnum = tipologiaFornituraEnum;
    }


    public Rifornitore(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public TipologiaFornituraEnum getTipoFornitura() {
        return tipologiaFornituraEnum;
    }

    public void setTipoFornitura(TipologiaFornituraEnum tipologiaFornituraEnum) {
        this.tipologiaFornituraEnum = tipologiaFornituraEnum;
    }


}
