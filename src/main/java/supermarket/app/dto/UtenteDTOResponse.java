package supermarket.app.dto;

public class UtenteDTOResponse {
    private Long id;
    private String username;
    private String name;
    private String cognome;

    public UtenteDTOResponse() {
    }

    public UtenteDTOResponse(Long id, String username, String name, String cognome) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.cognome = cognome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
