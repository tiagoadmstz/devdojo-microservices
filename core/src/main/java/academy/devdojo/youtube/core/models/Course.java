package academy.devdojo.youtube.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOV_GUIA")
public class Course implements AbstractEntity {

    @Id
    @Column(name = "NUMERO_GUIA")
    private Long id;

}
