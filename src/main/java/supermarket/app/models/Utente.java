package supermarket.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import supermarket.app.enums.RoleEnum;

import java.util.Collection;
import java.util.List;
@Entity
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    @Length(min = 2, max = 19, message = "Il username deve essere compreso tra 2 e 19 caratteri")
    @NotBlank
    private String username;

    @Column
    @NotBlank(message = "Il campo deve essere necessariamente riempito")
    private String password;
    @Column
    @Length(min = 2, max = 19, message = "Il nome deve essere compreso tra 2 e 19 caratteri")
    @NotBlank
    private String name;

    @Column
    @Length(min = 2, max = 19, message = "Il cognome deve essere compreso tra 2 e 19 caratteri")
    @NotBlank
    private String cognome;

    @Enumerated(EnumType.STRING) //enum
    private RoleEnum role;

    @ManyToOne
    @JoinColumn(name = "negozio_id")
    private Negozio negozio;

    public Utente() {
    }

    public Utente(Long id, String username, String password, String name, String cognome, RoleEnum role, Negozio negozio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.cognome = cognome;
        this.role = role;
        this.negozio = negozio;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Negozio getNegozio() {
        return negozio;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }
}
