package supermarket.app.dto;

public class UtenteDTOSignUp {
    private String name;
    private String username;
    private String password;
    private String cognome;



    public UtenteDTOSignUp(String name, String username, String password, String cognome) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.cognome = cognome;
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
}
