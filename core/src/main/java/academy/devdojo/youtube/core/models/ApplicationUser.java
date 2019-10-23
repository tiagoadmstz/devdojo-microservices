package academy.devdojo.youtube.core.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "CAD_USUARIO_PRONTUARIO")
public class ApplicationUser implements AbstractEntity {

    @Id
    @Column(name = "USUARIO")
    private Long id;
    @Column(name = "USUARIO_ID")
    private String username;
    @ToString.Exclude
    @Column(name = "PASSWD")
    private String password;
    @Transient
    private String role = "USER";

    public ApplicationUser(@NotNull ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();
    }

}
