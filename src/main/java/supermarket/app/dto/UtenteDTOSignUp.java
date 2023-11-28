package supermarket.app.dto;

public class UtenteDTOSignUp {
    private String name;
    private String username;
    private String password;
    private String cognome;
    private Long negozio_id;


    public UtenteDTOSignUp(String name, String username, String password, String cognome, Long negozio_id) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.cognome = cognome;
        this.negozio_id = negozio_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Long getNegozio_id() {
        return negozio_id;
    }

    public void setNegozio_id(Long negozio_id) {
        this.negozio_id = negozio_id;
    }
}
