package supermarket.app.dto;

import supermarket.app.enums.MansioneEnum;

public class DipendenteDTOResponse{
    private Long id;
    private String nome;
    private String cognome;
    private MansioneEnum mansioneEnum;
    private double stipendio;
    private Long negozio_id;

    public DipendenteDTOResponse() {
    }

    public DipendenteDTOResponse(Long id, String nome, String cognome, MansioneEnum mansioneEnum, double stipendio, Long negozio_id) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.mansioneEnum = mansioneEnum;
        this.stipendio = stipendio;
        this.negozio_id = negozio_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNegozio_id() {
        return negozio_id;
    }

    public void setNegozio_id(Long negozio_id) {
        this.negozio_id = negozio_id;
    }
}
